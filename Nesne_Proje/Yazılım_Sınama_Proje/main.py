import sounddevice as sd
import librosa
import librosa.display
import numpy as np
import speech_recognition as sr
import tkinter as tk
from tkinter import messagebox
from tkinter.font import Font
import matplotlib.pyplot as plt
import os
from person_identifier import identify_person
from gtts import gTTS
from playsound import playsound
from scipy.io.wavfile import write
import joblib
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from sklearn.feature_extraction.text import TfidfVectorizer


emotion_model = joblib.load("emotion_classifier.pkl")


happy_words = ["mutlu", "sevinçli", "neşeli", "güzel", "harika", "iyi", "pozitif", "keyifli", "memnun", "coşkulu"]
sad_words = ["üzgün", "mutsuz", "kötü", "berbat", "hayal kırıklığı", "ağlamak", "hüzünlü", "karamsar", "umutsuz"]
angry_words = ["kızgın", "sinirli", "öfke", "çıldırmak", "nefret", "lanet", "öfkelendim", "bıktım", "delirdim"]


def speak(text, lang='tr'):
    try:
        tts = gTTS(text=text, lang=lang)
        tts.save("output.mp3")
        playsound("output.mp3")
        os.remove("output.mp3")
    except Exception as e:
        messagebox.showerror("Hata", f"Sesli geri bildirim sırasında hata oluştu: {e}")


def record_audio(filename="realtime_audio.wav", duration=7, sample_rate=44100):
    messagebox.showinfo("Kayıt", "Kayıt başlıyor... Lütfen 7 saniye boyunca konuşun.")
    recording = sd.rec(int(duration * sample_rate), samplerate=sample_rate, channels=1, dtype='int16')
    sd.wait()
    write(filename, sample_rate, recording)
    messagebox.showinfo("Kayıt", f"Kayıt tamamlandı: {filename}")


def extract_emotion_features(file_path):
    y, sr = librosa.load(file_path)
    mfcc = np.mean(librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13), axis=1)
    return mfcc.reshape(1, -1)


def speech_to_text(filename):
    recognizer = sr.Recognizer()
    with sr.AudioFile(filename) as source:
        audio_data = recognizer.record(source)
        try:
            return recognizer.recognize_google(audio_data, language="tr-TR")
        except sr.UnknownValueError:
            return "Konuşma anlaşılamadı."
        except sr.RequestError:
            return "Google API hizmetine ulaşılamadı."


def analyze_text_sentiment(text):
    text = text.lower()
    words = text.split()

    happy_count = sum(1 for word in words if word in happy_words)
    sad_count = sum(1 for word in words if word in sad_words)
    angry_count = sum(1 for word in words if word in angry_words)

    if happy_count > sad_count and happy_count > angry_count:
        return "happy"
    elif sad_count > happy_count and sad_count > angry_count:
        return "sad"
    elif angry_count > happy_count and angry_count > sad_count:
        return "angry"
    else:
        return "neutral"


def count_words(text):
    return len(text.split())


def analyze_topic(text):
    nltk.download('punkt', quiet=True)
    nltk.download('stopwords', quiet=True)

    stop_words = set(stopwords.words("turkish"))
    tokenizer = nltk.tokenize.ToktokTokenizer()  
    words = tokenizer.tokenize(text.lower())
    filtered_words = [word for word in words if word.isalnum() and word not in stop_words]


    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform([" ".join(filtered_words)])
    scores = zip(vectorizer.get_feature_names_out(), tfidf_matrix.toarray()[0])
    sorted_scores = sorted(scores, key=lambda x: x[1], reverse=True)

    keywords = [word for word, _ in sorted_scores[:10]]
    return keywords


def determine_topic(keywords):
   
    
    topic_map = {
        "teknoloji": "Bilim ve Teknoloji",
        "yazılım": "Bilim ve Teknoloji",
        "mutluluk": "Pozitif Duygular",
        "sevinç": "Pozitif Duygular",
        "üzüntü": "Negatif Duygular",
        "ağlamak": "Negatif Duygular",
    }

    for kw in keywords:
        if kw in topic_map:
            return topic_map[kw]

    return "Genel Konu"


def plot_acc_and_fm(filename):
    y, sr = librosa.load(filename)

    plt.figure(figsize=(12, 6))

    
    plt.subplot(2, 1, 1)
    plt.plot(np.arange(len(y)) / sr, y)
    plt.title("ACC Grafiği (Amplitüd vs Zaman)")
    plt.xlabel("Zaman (s)")
    plt.ylabel("Amplitüd")

    
    plt.subplot(2, 1, 2)
    freqs = np.fft.fftfreq(len(y), d=1/sr)
    fft_magnitude = np.abs(np.fft.fft(y))
    plt.plot(freqs[:len(freqs)//2], fft_magnitude[:len(fft_magnitude)//2])
    plt.title("FM Grafiği (Frekans Modülasyonu)")
    plt.xlabel("Frekans (Hz)")
    plt.ylabel("Genlik")

    plt.tight_layout()
    plt.show()


def record_and_predict():
    filename = "realtime_audio.wav"

    
    record_audio(filename)

    
    person = identify_person(filename)
    person_label.config(text=f"Tanımlanan Kişi: {person}", fg="#0066cc")
    speak(f"Tanımlanan kişi {person}")

    
    features = extract_emotion_features(filename)
    emotion = emotion_model.predict(features)[0]
    result_label.config(text=f"Tahmin Edilen Ses Duygusu: {emotion}", fg="#009933")
    speak(f"Tahmin edilen ses duygusu {emotion}")

    
    text = speech_to_text(filename)
    text_label.config(text=f"Dönüştürülen Metin: {text}", fg="#333333")

    
    text_sentiment = analyze_text_sentiment(text)
    text_sentiment_label.config(text=f"Tahmin Edilen Metin Duygusu: {text_sentiment}", fg="#ff9933")
    speak(f"Tahmin edilen metin duygusu {text_sentiment}")

    
    word_count = count_words(text)
    word_count_label.config(text=f"Kelime Sayısı: {word_count}", fg="#9933ff")


    if text and text != "Konuşma anlaşılamadı.":
        keywords = analyze_topic(text)
        topic_label.config(text=f"Anahtar Kelimeler: {', '.join(keywords)}", fg="#0066cc")

        
        topic = determine_topic(keywords)
        topic_result_label.config(text=f"Konu: {topic}", fg="#0066cc")

    else:
        topic_label.config(text="Konu analizi yapılamadı.", fg="red")
        topic_result_label.config(text="", fg="red")

    
    plot_acc_and_fm(filename)

root = tk.Tk()
root.title("Gerçek Zamanlı Kişi, Duygu ve Konu Tanıma")
root.geometry("600x850")
root.configure(bg="#f2f2f2")

header_font = Font(family="Helvetica", size=16, weight="bold")
text_font = Font(family="Helvetica", size=12)

header = tk.Label(root, text="Duygu, Konu ve Kişi Tanıma Sistemi", font=header_font, bg="#f2f2f2", fg="#333333")
header.pack(pady=20)

btn = tk.Button(root, text="Konuş ve Tahmin Et", command=record_and_predict, font=("Helvetica", 14), bg="#007acc", fg="white", width=20)
btn.pack(pady=20)

person_label = tk.Label(root, text="", font=text_font, bg="#f2f2f2")
person_label.pack(pady=10)

result_label = tk.Label(root, text="", font=text_font, bg="#f2f2f2")
result_label.pack(pady=10)

text_label = tk.Label(root, text="", wraplength=400, justify="center", font=text_font, bg="#f2f2f2")
text_label.pack(pady=10)

text_sentiment_label = tk.Label(root, text="", font=text_font, bg="#f2f2f2")
text_sentiment_label.pack(pady=10)

word_count_label = tk.Label(root, text="", font=text_font, bg="#f2f2f2")
word_count_label.pack(pady=10)

topic_label = tk.Label(root, text="", wraplength=400, justify="center", font=text_font, bg="#f2f2f2")
topic_label.pack(pady=10)

topic_result_label = tk.Label(root, text="", wraplength=400, justify="center", font=text_font, bg="#f2f2f2")
topic_result_label.pack(pady=10)

root.mainloop()

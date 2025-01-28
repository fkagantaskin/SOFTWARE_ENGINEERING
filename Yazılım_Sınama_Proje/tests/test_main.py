import unittest
from main import analyze_text_sentiment, extract_emotion_features
import numpy as np

class TestAudioTextEmotions(unittest.TestCase):
    

    def test_audio_emotion_not_none(self):
        """
        Test Case #1: Ses Duygusu (Audio)
        - Modelin "happy/sad/angry/neutral" gibi bir sonuç döndürdüğünü kontrol ederiz.
        """
        
        dummy_mfcc = np.random.rand(1, 13)  
        
        
        from joblib import load
        audio_model = load("emotion_classifier.pkl")  
        emotion = audio_model.predict(dummy_mfcc)[0]

        self.assertIn(emotion, ["happy", "sad", "angry", "neutral"],
                      msg=f"Ses duygu analizi beklenenden farklı: {emotion}")

    def test_text_sentiment_not_none(self):
        """
        Test Case #2: Metin Duygusu (Text)
        - analyze_text_sentiment fonksiyonuyla, metinde hiçbir 'mutlu' 
          ya da 'üzgün' ifadesi geçmiyor olsa bile 'happy', 'sad', 'angry', 'neutral' 
          gibi bir çıktı dönmesini bekliyoruz.
        - Bu testi geçmek için tek şart, fonksiyonun None veya boş dönmemesi.
        """
        text = "Bu cümlede pozitif veya negatif içerik yok gibi."
        result = analyze_text_sentiment(text)
        self.assertIn(result, ["happy", "sad", "angry", "neutral"],
                      msg=f"Metin duygu analizi beklenenden farklı: {result}")

    def test_no_comparison_needed(self):
        """
        Test Case #3: Ses ve Metin Duygularını Aynı Anda Çağırma, 
        - Bir senaryoda hem ses (dummy) hem de metin analizi yaparız.
        - Elde edilen iki sonucu da sadece 'None' veya beklenen set 
          içinde olup olmadığını denetleriz.
        - Bu iki sonucun aynı olması veya farklı olması TESTİ ETKİLEMEZ,
          hata sayılmaz.
        """
        
        dummy_mfcc = np.random.rand(1, 13)
        from joblib import load
        audio_model = load("emotion_classifier.pkl")
        audio_emotion = audio_model.predict(dummy_mfcc)[0]

        
        text = "Harika bir gün olacağını umut ediyorum."
        text_emotion = analyze_text_sentiment(text)

        
        self.assertIn(audio_emotion, ["happy", "sad", "angry", "neutral"])
        self.assertIn(text_emotion, ["happy", "sad", "angry", "neutral"])

        

if __name__ == "__main__":
    unittest.main()

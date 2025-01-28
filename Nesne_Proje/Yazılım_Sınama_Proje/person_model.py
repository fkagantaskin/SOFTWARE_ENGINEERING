import librosa
import numpy as np
import joblib
from sklearn.ensemble import RandomForestClassifier

data = []
labels = []

file_paths = [
    ("dataset/sad/Uzgun_Laura.mp3", "Laura"),
    ("dataset/sad/Uzgun_Aria.mp3", "Aria"),
    ("dataset/sad/Uzgun_Bill.mp3", "Bill"),
    ("dataset/sad/Uzgun_Brian.mp3", "Brian"),
    ("dataset/sad/Uzgun_Jessica.mp3", "Jessica"),
    ("dataset/sad/Uzgun_Liam.mp3", "Liam"),
    ("dataset/sad/Uzgun_Rachel.mp3", "Rachel"),
    ("dataset/sad/Uzgun_Sarah.mp3", "Sarah"),
    ("dataset/happy/Mutlu_Laura.mp3", "Laura"),
    ("dataset/happy/Mutlu_Aria.mp3", "Aria"),
    ("dataset/happy/Mutlu_Bill.mp3", "Bill"),
    ("dataset/happy/Mutlu_Brian.mp3", "Brian"),
    ("dataset/happy/Mutlu_Jessica.mp3", "Jessica"),
    ("dataset/happy/Mutlu_Liam.mp3", "Liam"),
    ("dataset/happy/Mutlu_Rachel.mp3", "Rachel"),
    ("dataset/happy/Mutlu_Sarah.mp3", "Sarah"),
    ("dataset/angry/Kizgin_Laura.mp3", "Laura"),
    ("dataset/angry/Kizgin_Aria.mp3", "Aria"),
    ("dataset/angry/Kizgin_Bill.mp3", "Bill"),
    ("dataset/angry/Kizgin_Brian.mp3", "Brian"),
    ("dataset/angry/Kizgin_Jessica.mp3", "Jessica"),
    ("dataset/angry/Kizgin_Liam.mp3", "Liam"),
    ("dataset/angry/Kizgin_Rachel.mp3", "Rachel"),
    ("dataset/angry/Kizgin_Sarah.mp3", "Sarah"),
]

for file, label in file_paths:
    try:
        y, sr = librosa.load(file)
        mfcc = np.mean(librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13), axis=1)
        data.append(mfcc)
        labels.append(label)
    except Exception as e:
        print(f"Hata: {file} dosyası yüklenemedi. {e}")

data = np.array(data)
labels = np.array(labels)

model = RandomForestClassifier()
model.fit(data, labels)

joblib.dump(model, "person_identifier.pkl")
print("Model başarıyla kaydedildi: person_identifier.pkl")

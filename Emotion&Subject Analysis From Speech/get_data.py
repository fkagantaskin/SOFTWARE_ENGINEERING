import os
import librosa
import numpy as np
import pandas as pd

DATASET_PATH = "dataset"
emotions = ["happy", "sad", "angry"]

data = []
labels = []

def extract_features(file_path):
    y, sr = librosa.load(file_path)
    mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13)
    return np.mean(mfcc, axis=1)


for emotion in emotions:
    folder_path = os.path.join(DATASET_PATH, emotion)
    for file_name in os.listdir(folder_path):
        file_path = os.path.join(folder_path, file_name)
        features = extract_features(file_path)
        data.append(features)
        labels.append(emotion)

df = pd.DataFrame(data)
df['label'] = labels
df.to_csv("audio_features.csv", index=False)

print("Özellik çıkarma tamamlandı.")

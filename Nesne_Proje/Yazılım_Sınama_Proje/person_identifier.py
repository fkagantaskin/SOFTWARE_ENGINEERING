import librosa
import numpy as np
import joblib

person_model = joblib.load("person_identifier.pkl")

def identify_person(filename):
    y, sr = librosa.load(filename)
    mfcc = np.mean(librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13), axis=1).reshape(1, -1)
    prediction = person_model.predict(mfcc)
    return prediction[0]

import pandas as pd
import joblib
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix


df = pd.read_csv("audio_features.csv")


X = df.drop('label', axis=1)
y = df['label']


X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42, stratify=y)



model = RandomForestClassifier()
X = X.values  
model.fit(X, y)


y_pred = model.predict(X_test)

print("Doğruluk Oranı:", accuracy_score(y_test, y_pred))
print("\nSınıflandırma Raporu:\n", classification_report(y_test, y_pred, zero_division=1))
print("\nKarışıklık Matrisi:\n", confusion_matrix(y_test, y_pred))


joblib.dump(model, "emotion_classifier.pkl")


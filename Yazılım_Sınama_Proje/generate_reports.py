import pandas as pd
import matplotlib.pyplot as plt

def load_data(filename="analysis_results.csv"):
    try:
        return pd.read_csv(filename)
    except FileNotFoundError:
        print(f"{filename} dosyası bulunamadı. Lütfen önce veri kaydedin.")
        return None


def plot_sentiment_distribution(df):
    plt.figure(figsize=(10, 6))

    
    plt.subplot(1, 2, 1)
    df["Audio Sentiment"].value_counts().plot(kind="bar", color="lightblue", edgecolor="black")
    plt.title("Ses Duygu Dağılımı")
    plt.xlabel("Duygular")
    plt.ylabel("Frekans")
    plt.xticks(rotation=0)

    
    plt.subplot(1, 2, 2)
    df["Text Sentiment"].value_counts().plot(kind="bar", color="lightgreen", edgecolor="black")
    plt.title("Metin Duygu Dağılımı")
    plt.xlabel("Duygular")
    plt.ylabel("Frekans")
    plt.xticks(rotation=0)

    plt.tight_layout()
    plt.show()


def plot_emotion_over_time(df):
    plt.figure(figsize=(12, 6))
    df["Timestamp"] = pd.to_datetime(df["Timestamp"])
    df = df.sort_values("Timestamp")

    plt.plot(df["Timestamp"], df["Audio Sentiment"], marker="o", linestyle="-", color="purple", label="Ses Duygusu")
    plt.plot(df["Timestamp"], df["Text Sentiment"], marker="o", linestyle="--", color="orange", label="Metin Duygusu")

    plt.title("Zaman İçinde Duygu Değişimi")
    plt.xlabel("Zaman")
    plt.ylabel("Duygular")
    plt.xticks(rotation=45)
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.show()


def plot_word_count_distribution(df):
    plt.figure(figsize=(8, 6))
    df["Word Count"].plot(kind="hist", bins=10, color="lightcoral", edgecolor="black")
    plt.title("Kelime Sayısı Dağılımı")
    plt.xlabel("Kelime Sayısı")
    plt.ylabel("Frekans")
    plt.grid(axis="y", linestyle="--", alpha=0.7)
    plt.tight_layout()
    plt.show()


def plot_word_count_vs_sentiment(df):
    plt.figure(figsize=(10, 6))
    plt.scatter(df["Word Count"], df["Audio Sentiment"], color="blue", alpha=0.6, label="Ses Duygusu")
    plt.scatter(df["Word Count"], df["Text Sentiment"], color="green", alpha=0.6, label="Metin Duygusu")

    plt.title("Kelime Sayısına Göre Duygular")
    plt.xlabel("Kelime Sayısı")
    plt.ylabel("Duygular")
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.show()


def main():
    filename = "analysis_results.csv"
    df = load_data(filename)

    if df is not None:
        print(df.head())
        plot_sentiment_distribution(df)
        plot_emotion_over_time(df)
        plot_word_count_distribution(df)
        plot_word_count_vs_sentiment(df)

if __name__ == "__main__":
    main()

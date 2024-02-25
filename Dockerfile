# Dockerfile

# OpenJDK 16 kullanarak bir Java çalışma zamanı imajını temel alın
FROM adoptopenjdk/openjdk16:alpine-jre

# Uygulama dosyalarını /usr/src/app dizinine kopyalayın
# COPY ./target/classes/ /usr/src/app/

# Çalışma dizinini /usr/src/app dizinine ayarlayın
# WORKDIR /usr/src/app

# Uygulamayı çalıştırmak için MySQL bekleyin
CMD ["sh", "-c", "echo 'MySQL için hazır'; sleep 1000"]
FROM ubuntu:latest
LABEL authors="hasan"

ENTRYPOINT ["top", "-b"]
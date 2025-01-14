FROM python:3.9-slim

WORKDIR /app

COPY . /app
RUN pip install -r requirements.txt

EXPOSE 5000

ENV FLASK_APP=app.app

CMD ["python", "-m", "flask", "run","--host=0.0.0.0","--port=5000"] 
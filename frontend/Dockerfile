# Basis-Image: Node.js
FROM node:25-alpine
# Arbeitsverzeichnis im Container festlegen
WORKDIR /app
# Package-Dateien kopieren (für effizientes Caching der Dependencies)
COPY package*.json ./
# Abhängigkeiten installieren
RUN npm install
# Den Rest des Codes kopieren
COPY . .
# Port für das Frontend öffnen (Vite nutzt standardmäßig 5173, CRA 3000)
EXPOSE 5173
# Startbefehl
# Das "--host" ist extrem wichtig, damit Vite Anfragen von außerhalb des Containers annimmt!
CMD ["npm", "run", "dev", "--", "--host"]
# 🎯 The Bilingual Treasure

**The Bilingual Treasure** es un juego educativo interactivo hecho en Java y JavaFX que ayuda a los jugadores a practicar la traducción entre inglés y español de manera divertida y contrarreloj.

## 🚀 ¿Cómo jugar?

1. Ingresa tu nombre.
2. Selecciona el idioma con el que quieres jugar (Español o Inglés).
3. Traduce la palabra que aparece antes de que se acabe el tiempo.
4. El juego termina cuando se acaban las palabras. ¡Revisa tu puntaje final!

## ✅ Requisitos

- [Descargar Java 21](https://www-oracle-com.translate.goog/java/technologies/javase/jdk21-archive-downloads.html?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=tc)
- [Descargar JavaFX SDK 21.0.7](https://gluonhq.com/products/javafx/)
- 
# Pasos para ejecutar los comandos:
- Navega hasta la carpeta del proyecto
```bash
  cd bilingualtreasure
  ```
```bash
xcopy /E /I src\main\resources\ out\
  ```
## 🛠️ Compila el código:


Para compilar el código fuente, usa el siguiente comando en tu terminal:

```bash
javac --module-path "javafx-sdk-21.0.7/lib" --add-modules javafx.controls,javafx.fxml -d out src\main\java\bilingualtreasure\*.java
```

## 🎮 Ejecuta el juego:
Una vez compilado el código, ejecuta el juego con el siguiente comando:

```bash
java --module-path "javafx-sdk-21.0.7/lib" --add-modules javafx.controls,javafx.fxml -cp out bilingualtreasure.BilingualTreasure
```

### 📦  (Opcional) Descargar y ejecutar .JAR

- [Descargar .ZIP](https://drive.google.com/file/d/1sFkG10gD8mgQ4_GLE_W32tQDees6J-wL/view?usp=sharing)
- Extrae el contenido del archivo ZIP.
- Ejecuta el archivo ```ejecutar.bat``` para iniciar el juego.

## 📁  Estructura del código

El código fuente está organizado en los siguientes archivos y directorios:
# Abilify


https://user-images.githubusercontent.com/56606048/214550498-148d83b1-2f40-45a8-8537-3649ff5968de.mp4


**ABSTRACT**

The primary objective of the project is to reduce the efforts of a visually impaired person to dial phone number is case of emergency, without the need of any special braille keyboard/hardware or an internet service. These objectives are achieved through an android application empowered with several computer vision algorithms which let the user to dial a phone number by just drawing the digits on their phone screen and recognizing these digits in real-time. The app also provides a real-time feedback of the recognised digits by reading them aloud to the user.

**PROBLEM STATEMENT**

In an era of accelerated technology where major task of our life can be done with just a click of phone, some are still struggling to carry out the basic tasks of their day-to-day life. Yes, they are visually impaired. We might not know how much difficult it is for a them to dial a number for making a phone call over a modern touch screen device, which is one of the most necessary tasks today specially at the time of emergency. The solutions for this problem available today are: -

- Using voice assistant.

- Using braille phone or the keyboards add-ons specially designed for visually impaired.

- Using assistive screen reader like google talkback, etc.

But, these solutions are quite hard to become part of user-life due to some of their underlined limitations: -

- Voice Assistants requires internet access as they cannot work offline.

- Other ways like braille keypad/keyboards and assistive screen reader are quite cumbersome to be used and needs a lot of practice.

Thus, today the visually impaired community is in an urgent need of an aid which is handy, user-

friendly, reliable and thus can be used easily to dial a phone number in case of an emergency.

The primary objective of the project is to reduce the efforts of a visually impaired person to dial phone number is case of emergency, without the need of special braille keyboard/hardware or an internet service. These objectives are achieved through an android application empowered with several computer vision algorithms which let the user to dial a phone number by just drawing the digits on their phone screen and recognizing these digits in real-time. In addition to the basic functionality, the app also aims at providing a voice assistance that can read out the digits entered/drawn by the user.

The user is provided access to the application user interface where he/she can draw digits of the number that is to be dialed. These digits are then recognized in real-time using a pre-trained machine learning model. Android *Text-to-Speech* is then operated on the recognized text to read-aloud the input digit. After obtaining 10 digits of the phone number in a digit-by-digit manner, the request is transferred to the default dialer app to initiate a phone call. In this way a user with visual impairment can dial a phone number in a fast and hassle-free manner without having the knowledge of braille keyboard/hardware or without any internet-access.

**KEY FEATURES**

- Access to full screen of the device to draw digits of the phone number that needs to be dialed.

- Realtime recognition of drawn pattern/digit.

- Virtual assistance through 'read-aloud' technology.

- Automatic call dialing after 10-digit phone number is obtained.

- Works Offline.

- Supported on all android devices with android version 5.0 or higher.

**TECH STACK**

**Python** programming language along with several frameworks  such  as  **TensorFlow, TensorFlow Lite,  Keras**, and libraries like **numpy, matplotlib**, etc. for implementing and training machine learning model.

**Google Colab** for executing python code with several dependencies on a virtual environment.

**Android Development Toolkit**

Android provides the following software toolkits for Android app development:

- Software Development Kit (SDK)

- Integrated Development Kit

- Java Software Development Kit (JDK)

- Gradle build system

SDK provides tools to create, debug, and emulate an application. IDE provides a GUI for SDK tools access and an interface to write codes. Android Studio is the preferred IDE. SDK packages come bundled up in Android Studio inform of SDK Manager. JDK provides an interpretation of the Java program.

**Kotlin** is a cross-platform, statically typed, general-purpose programming language with type inference. Kotlin is designed to interoperate fully with Java and ease the process of android application development.

**METHODOLOGY AND ALGORITHMS FORMULATED**

The architecture of the Dialing-Aid system is as follows: -

1. The system consists of an Android application which can run any android device without any internet access.

2. The *Main Activity* of the application is composed of *Drawable View* where user can draw digits of the number that is to be dialed.

3. Each pattern drawn by the user on the *drawable view* is passed to a pre-trained Machine Learning model as a Bitmap image of 28x28 pixels that uses *Convolution Neural Network* to recognize the digit out of the drawing image.

4. Each recognized digit is then operated upon by Android *Text-to-speech* converter which provides real-time feedback of the recognized digit by reading it out to the user. Android *Text-to-speech* converter is also an AI-based system with *natural language processing (NLP)* capabilities. The NLP Engine generates human-like voices, which make the text more interactive and fluent.

5. The *drawable* view is then automatically cleared to prepare itself for the next drawing.

6. The above process repeats itself until user has entered 10 digits of phone number.

7. After getting all the 10 digits of the phone number, the app then request a phone call on the obtained phone number by communicating with the default dialer app of the system.

8. In this way a user with visual impairment can dial a phone number in a fast and hassle-free manner without having the knowledge of braille keyboard/hardware or without any internet-access.


Machine Learning Model for Digit Classification
-----------------------------------------------

In this project, a *Convolution Neural Network* was implemented using *Keras* APIs, trained and tested on MNIST dataset and deployed in our android application to recognize handwritten digits. 
The following is the overview of the ML-Model deployed in this project:-
- Model: Convolutional Neural Network
- Input: 28 x 28 grayscale image
- Output: A float array of length 10 representing the probability of the image being a digit from 0 to 9.
- No. of Hidden Layers: 7
- Activation Function: ReLU
- Kernel Size: 3x3
- Pool Size: 2x2
- Optimizer: Adam
- Training Dataset: [MNIST](https://en.wikipedia.org/wiki/MNIST_database)

# Deep Diagnosis
This is a research oriented project where we have tried to use machine learning models for classification tasks using images as an input. We've used CNN model for the task of image analysis and classification. The architecture of our model is a standard architecture used by many in the industry. The architecture is called Inception model.

The model architecture can be found here => <a href="http://www.cv-foundation.org/openaccess/content_cvpr_2015/papers/Szegedy_Going_Deeper_With_2015_CVPR_paper.pdf">Inception Model</a><br>
We've trained the model on respective datasets and pickled them for easy use.
Information regarding the datasets can be found in the references section.


# Contents
* Authors
* Requirements
* How to get it working
* Integration
* References
* Liscense

# Authors
* Danish Shah
* Jawad Shaikh
* Afzal Sayed
* Aditya Mishra
* Maaz Khan

# Requirements
* Python 3.X.X
* Flask
* TensorFlow

# How to get it working?

## The Core
=> The core part of the project is simply a program that loads in the trained model and passes an image through it for classification.<br>
=> To run the core section simply clone the repo. copy the "predict.py" file from the root into your project folder.<br>
=> Download the trained model pickels from the following link => <a href="https://drive.google.com/drive/folders/0B0eNSOFz7zL7Q2JqN0E5R2NZNXM?usp=sharing">Trained Models</a>
=> Place the "trained_nets_Mumbai_hackathon" folder into you project directory.
=> Now we simply fire up a Python shell or write the following python program.
* Import the module
``` python
import predict.py as pred
```
* Now we can simply call the below function to classify skin lesion image. (either benign or malignant)
``` python
# method for classifying skin lesions
# prints a string stating whether the lesion is melignant(Cancerous) or benign(non-cancerous) with a percent confidence
pred.classifySkinLesion(image)
```
* Or we could classify the severity of retinopathy using a clinical image of patient's retina
``` python
# method for classifying diabetic retinopathy
# prints a string stating whether the image has a chance of diabetic ratinopathy (Normal, Moderate, Severe) with a percent confidence.
pred.classifyDiabeticRetinopathy(image);
```
=> The images should be in the same folder as the "predict.py" file their name should be passed as an argument to the function.

## The Web App
=> Running the web app is even simpler. Just place the trained_nets folder that we've downloaded in the above section and place it in the directory "Web/Backend/"
=> In the above directory, you'll find a main.py file. That is the file that will launch our Flask app.
=> Simply open a command line and type in :
``` python
python main.py
```
=> The app wil start locally and the command line will show the URL to access the app.

# Integration
* We've integrated the trained model with a web app that we explored in the last section and an Android Applicaion.
* The screens of the Android app is given below.
* The source of the app can be found in the repo under folder "Android". 

## Screenshots (Android)

 .             |   .
:-------------------------:|:-------------------------:
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170331[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170326[1].png)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170321[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170306[1].png)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170313[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170618[1].png)




# Contribution
* Want to contribute?
Contribute by adding more dataset for training the model.

# Liscense
This project is licensed under MIT License. 

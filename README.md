# Deep Diagnosis
This is a research oriented project where we have tried to use machine learning models for classification tasks using images as an input. We've used CNN model for the task of image analysis and classification. The architecture of our model is a standard architecture used by many in the industry. The architecture is called Inception model.

The model architecture can be found here => <a href="http://www.cv-foundation.org/openaccess/content_cvpr_2015/papers/Szegedy_Going_Deeper_With_2015_CVPR_paper.pdf">Inception Model</a><br>
We've trained the model on respective datasets and pickled them for easy use.
Information regarding the datasets can be found in the [References](#references) section.

<img src="https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/GooglePlayStore.png" width="200">


## Contents
* [Requirements](#requirements)
* [How to get it working](#how-to-get-it-working?)
* [Integration](#integration)
* [References](#references)
* [Authors](#authors)
* [Liscense](#liscense)

## Requirements
* Python 3.X.X
* Flask
* TensorFlow

## How to get it working?

### The Core
1. The core part of the project is simply a program that loads in the trained model and passes an image through it for classification.<br>
2. To run the core section simply clone the repo. copy the "predict.py" file from the root into your project folder.<br>
3. Download the trained model pickels from the following link => <a href="https://drive.google.com/drive/folders/0B0eNSOFz7zL7Q2JqN0E5R2NZNXM?usp=sharing">Trained Models</a>
4. Place the "trained_nets_Mumbai_hackathon" folder into you project directory.
5. Now we simply fire up a Python shell or write the following python program.
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
6. The images should be in the same folder as the "predict.py". Their file name should be passed as an argument to the function.

### The Web App
1. Running the web app is even simpler. Just place the trained_nets folder that we've downloaded in the above section and place it in the directory "Web/Backend/"
2. In the above directory, you'll find a main.py file. That is the file that will launch our Flask app.
3. Simply open a command line and type in :
``` python
python main.py
```
4. The app wil start locally and the command line will show the URL to access the app.

## Integration
* We've integrated the trained model with a web app that we explored in the last section and an Android Applicaion.
* The screens of the Android app is given below.
* The source of the app can be found in the repo under folder "Android". 

### Screenshots (Android)

 .             |   .
:-------------------------:|:-------------------------:
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170331[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170326[1].png)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170321[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170306[1].png)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170313[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170618[1].png)

### Screenshots (Web App)

![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/web_1.jpg)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/web_2.jpg)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/web_3.jpg)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/web_4.jpg)


## References
* Download the trained model pickels from the following link => <a href="https://drive.google.com/drive/folders/0B0eNSOFz7zL7Q2JqN0E5R2NZNXM?usp=sharing">Trained Models</a>
* Want to train your own Inception model? Check out this link : <a href="https://github.com/tensorflow/models/tree/master/inception">Training Inception!</a>
* We used two datasets for training our models.
  * We used the [ISIC](https://isic-archive.com/) dataset for skin lesion classification task. It is a set of 5 different datasets out of which we used only 4 and skipped the one names "ISIC_SONIC-1". We still had a very skewed data proportions (around 1500 images for benign category and only 600 images for malignant). So we went ahead and wrote a python script to flip all the images in the malignant folder to double the size and add new features.
    > We recieved a 79% accuracy on this model.
  * The second dataset that we used was from this [Kaggle competition](https://www.kaggle.com/c/diabetic-retinopathy-detection). We used only 6000 images from this due to time constraints.
    > This model gave us an accuracy of 68%.
* Paper that inspired this project:
  * [Rethinking the Inception Model](https://arxiv.org/pdf/1512.00567.pdf)
  

## Authors
* [Danish Shah](https://github.com/DanishShah)
* [Jawad Shaikh](https://github.com/jawadsh123)
* [Afzal Sayed]()
* [Aditya Mishra]()
* [Maaz Khan](https://github.com/maazrk)

## Liscense
This project is licensed under <a href="https://github.com/DanishShah/DeepDiagnosis/blob/master/LICENSE.txt">MIT License</a>



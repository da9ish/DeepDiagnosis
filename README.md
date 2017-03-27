# DeepDiagnosis
Skin Lesion Classification using Deep Learning

# Contents
* Requirements
* Installation
* Usage
* Integration
* Contribution
* Liscense

# Requirements
* Python 3.X.X
* Flask
* TensorFlow

# How to setup
* Install TensorFlow
* Clone the repo and execute the following command
to be added by Jawad

# Usage
``` python
              import predict.py as pred
              # method for classifying skin lesions
              # returns a string stating whether the lesion is melignant(Cancerous) or benign(non-cancerous) with a percent confidence
              pred.classifySkinLesion(image);
              
              # method for classifying diabetic retinopathy
              # returns a string stating whether the image has a chance of diabetic ratinopathy (Normal, Moderate, Severe) with a percent confidence.
              pred.classifyDiabeticRetinopathy(image);
              
              # Your Program.
```

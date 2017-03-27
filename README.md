# DeepDiagnosis
Skin Lesion Classification using Deep Learning

# Contents
* Authors
* Requirements
* Installation
* Usage
* Integration
* Contribution
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

# How to setup
* Install TensorFlow
* Clone the repo and execute the following command
to be added by Jawad

# Usage
Import predict.py to your project
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

# Integration
* DeepDiagnosis can be integrated with any device or technology.

# Example
* Example of DeepDiagnosis integrated on an Android Device.
<img src="https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170331[1].png"/>
<img src="https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170326[1].png"/>
<img src="https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170321[1].png"/>
<img src="https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170306[1].png"/>
<img src="https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170313[1].png"/>
<img src="https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170618[1].png"/>

* Example of DeepDiagnosis integrated on Web.

# Contribution
* Want to contribute?
Contribute by adding more dataset for training the model.

# Liscense
MIT License

Copyright (c) 2017 Danish Shah

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

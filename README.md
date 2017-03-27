# Deep Diagnosis
This is a research oriented project where we have tried to use machine learning models for classification tasks using images as an input. We've used CNN model for the task of image analysis and classification. The architecture of our model is a standard architecture used by many in the industry. The architecture is called Inception model.
The model architecture can be found here => <a href="http://www.cv-foundation.org/openaccess/content_cvpr_2015/papers/Szegedy_Going_Deeper_With_2015_CVPR_paper.pdf">Inception Model</a>

# Contents
* Authors
* Requirements
* How to get it working
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

# How to get it working?
## The Core
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


 .             |   .
:-------------------------:|:-------------------------:
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170331[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170326[1].png)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170321[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170306[1].png)
![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170313[1].png)  |  ![](https://github.com/DanishShah/DeepDiagnosis/blob/master/Resources/Screenshot_20170327-170618[1].png)



* Example of DeepDiagnosis integrated on Web.

# Contribution
* Want to contribute?
Contribute by adding more dataset for training the model.

# Liscense
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/   

   Copyright 2017 Danish Shah

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

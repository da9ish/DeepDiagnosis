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

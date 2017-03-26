import os
from flask import *
from returndata import Content
import predict as pred
from werkzeug.utils import secure_filename

UPLOAD_FOLDER = './static/UPLOADFOLDER'

ALLOWED_EXTENSIONS = set(['png', 'jpg', 'jpeg', 'bmp'])



TOPIC_DICT = Content()
app = Flask(__name__)
app._static_folder = "static"
@app.route('/')
def homepage():
    return render_template("index.html")


@app.route('/skin/')
def skin():
    return render_template("skinLesion.html")

@app.route('/retinopathy/')
def retinopathy():
    return render_template("retinopathy.html")


@app.route('/upload/', methods=["POST"])
def upload():
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            print(request.files)
            d = Response("Error",status=201,mimetype='application/json')
            return d
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            d = Response("No filename",status=201,mimetype='application/json')
            return d
        if file:
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            return pred.classify(filename, response=True)

@app.route('/uploadPC/', methods=["POST"])
def uploadPC():
    print("in upload")
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            print(request.files)
            d = Response("Error",status=201,mimetype='application/json')
            return d
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            d = Response("No filename",status=201,mimetype='application/json')
            return d
        if file:
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            category, confidence = pred.classify(filename, response=False)
            print(category, confidence)
            confidence = '%.2f'%(confidence*100)
            if category == "malignant":
                return render_template("malignant.html", confidence=confidence)
            else:
                return render_template("benign.html", confidence=confidence)

#FOR DIABETIC RETINOPATHY


@app.route('/uploadDiabetic/', methods=["POST"])
def uploadDiabetic():
    
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            print(request.files)
            d = Response("Error",status=201,mimetype='application/json')
            return d
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            d = Response("No filename",status=201,mimetype='application/json')
            return d
        if file:
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            return pred.classify_retino(filename, response=True)

@app.route('/uploadPCDiabetic/', methods=["POST"])
def uploadPCDiabetic():
    print("in upload")
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            print(request.files)
            d = Response("Error",status=201,mimetype='application/json')
            return d
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            d = Response("No filename",status=201,mimetype='application/json')
            return d
        if file:
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            category, confidence = pred.classify(filename, response=False)
            print(category, confidence)
            confidence = '%.2f'%(confidence*100)
            if category == "0":
                return render_template("diabeticNoDr.html", confidence=confidence)
            elif category == '1':
                return render_template("diabeticModerate.html", confidence=confidence)
            else:
                return render_template("diabeticSevere.html", confidence=confidence)










if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8000, debug=True)
    #app.run()

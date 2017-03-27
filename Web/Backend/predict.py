import tensorflow as tf, sys
from flask import *

# change this as you see fit


def classify(filename, response=False):

    image_path = "./static/UPLOADFOLDER/"
    image_path += filename
    # Read in the image_data
    image_data = tf.gfile.FastGFile(image_path, 'rb').read()

    # Loads label file
    label_lines = [line.rstrip() for line 
                       in tf.gfile.GFile("trained_nets_Mumbai_hackathon/ISIC_3/retrained_labels.txt")]

    # Unpersists graph from file
    with tf.gfile.FastGFile("trained_nets_Mumbai_hackathon/ISIC_3/retrained_graph.pb", 'rb') as f:
        graph_def = tf.GraphDef()
        graph_def.ParseFromString(f.read())
        _ = tf.import_graph_def(graph_def, name='')

    with tf.Session() as sess:
        # Feed the image_data as input to the graph and get first prediction
        softmax_tensor = sess.graph.get_tensor_by_name('final_result:0')
        
        predictions = sess.run(softmax_tensor, \
                 {'DecodeJpeg/contents:0': image_data})
        
        # Sort to show labels of first prediction in order of confidence
        top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]

        benign_malignant = ''
        maxi = 0
        
        for node_id in top_k:
            human_string = label_lines[node_id]
            score = predictions[0][node_id]
            print('%s (score = %.5f)' % (human_string, score))
            if score > maxi:
                benign_malignant = human_string
                maxi = score
    if response == True:
        return Response(benign_malignant+" "+str(maxi), status=200, mimetype='application/json')
    else:
        return benign_malignant, maxi

def classify_retino(filename, response=False):
    image_path = "./static/UPLOADFOLDER/"
    image_path += filename
    print(image_path)
    # Read in the image_data
    image_data = tf.gfile.FastGFile(image_path, 'rb').read()

    # Loads label file, strips off carriage return
    label_lines = [line.rstrip() for line 
                       in tf.gfile.GFile("trained_nets_Mumbai_hackathon/retino/retrained_labels.txt")]

    # Unpersists graph from file
    with tf.gfile.FastGFile("trained_nets_Mumbai_hackathon/retino/retrained_graph.pb", 'rb') as f:
        graph_def = tf.GraphDef()
        graph_def.ParseFromString(f.read())
        _ = tf.import_graph_def(graph_def, name='')

    with tf.Session() as sess:
        # Feed the image_data as input to the graph and get first prediction
        softmax_tensor = sess.graph.get_tensor_by_name('final_result:0')
        
        predictions = sess.run(softmax_tensor, \
                 {'DecodeJpeg/contents:0': image_data})
        
        # Sort to show labels of first prediction in order of confidence
        top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]

        category = ''
        maxi = 0
        
        for node_id in top_k:
            human_string = label_lines[node_id]
            score = predictions[0][node_id]
            print('%s (score = %.5f)' % (human_string, score))
            if score > maxi:
                category = human_string
                maxi = score
    if response == True:
        return Response(category+" "+str(maxi), status=200, mimetype='application/json')
    else:
        return category, maxi
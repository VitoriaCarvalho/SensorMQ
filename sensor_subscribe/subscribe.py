import paho.mqtt.client as paho
from pygame import mixer
from time import sleep

def on_subscribe(client, userdata, mid, granted_qos):
    print("Subscribed: "+str(mid)+" "+str(granted_qos))

def on_message(client, userdata, msg):
	if str(msg.payload) == "b'perto'":
		print(msg.topic+" "+str(msg.qos)+" "+str(msg.payload))    
		mixer.init()
		mixer.music.load('audio.mpeg')
		mixer.music.play()
	else:
	    mixer.music.stop()

client = paho.Client()
client.on_subscribe = on_subscribe
client.on_message = on_message
client.connect('10.180.14.64', 1883)
client.subscribe('proximidade/celular', qos=1)

client.loop_forever()

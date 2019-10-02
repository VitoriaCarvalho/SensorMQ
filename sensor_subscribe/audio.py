from pygame import mixer  # Load the popular external library
from time import sleep

mixer.init()
mixer.music.load('home/jederson_luz/git/SensorMQ/sensor_subscribe/WhatsApp%20Audio%202019-10-01%20at%2022.30.48.mpeg')
mixer.music.play()

sleep(10)

mixer.music.stop()

print('parou')

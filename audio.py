from pygame import mixer  # Load the popular external library
from time import sleep

mixer.init()
mixer.music.load('/home/vitoria/Downloads/WhatsApp Audio 2019-10-01 at 22.30.48.mpeg')
mixer.music.play()

sleep(10)

mixer.music.stop()

print('parou')
class Animal(object):
    def __init__(self,positionX,positionY):
        self.__positionX = positionX
        self.__positionY = positionY
        self.__lastPositionX = positionX
        self.__lastPositionY = positionY
    
    
    def move(self, positionX, positionY):
        self.__lastPositionX = self.__positionX
        self.__lastPositionY = self.__positionY
        self.__positionX = positionX
        self.__positionY = positionY

    def getLastPositionX(self):
        return self.__lastPositionX

    def getLastPositionY(self):
        return self.__lastPositionY

    def getPositionX(self):
        return self.__positionX
    
    def getPositionY(self):
        return self.__positionY

    def __str__(self):
        return str(self.__positionX)+", "+str(self.__positionY)
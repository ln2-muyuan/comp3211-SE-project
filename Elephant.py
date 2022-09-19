from Animal import Animal

class Elephant(Animal):
    def __init__(self, positionX, positionY):
        super().__init__(positionX, positionY)
        self.rank = 3

    def __str__(self):
        return "Elephant: "+super().__str__()



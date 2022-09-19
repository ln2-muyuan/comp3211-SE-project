from Elephant import Elephant

class Chessboard():
    def __init__(self):
        Elephant1 = Elephant(2, 1)
        self.__chessboard = [[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,3,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]]

    def update(self, Animal):
        x1=Animal.getLastPositionX()
        y1=Animal.getLastPositionY()
        x2=Animal.getPositionX()
        y2=Animal.getPositionY()
        self.__chessboard[x1][y1]=0
        self.__chessboard[x2][y2]=Animal.rank

    def __str__(self):
        string = ""
        for i in range(9):
            for j in range(7):
                string += str(self.__chessboard[i][j])+"  "
            string += "\n"
        return string
        # return str(self._chessboard)





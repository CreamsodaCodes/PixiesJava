import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class Sprite {
    int xpos;
    int ypos;
    int length;
    int color;
    boolean isDead = false;
    int red;
    int green;
    int blue;
    int whereInMovCircle = 1;
    int maxDelay1 = 8;
    int maxDelay2 = 15;
    int maxDelay3 = 50;
    int currentDely = 0;
    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        if (xpos < 0+10 || xpos+length > Settings.getGirdLenght()-10) {
            return;
        }
        this.xpos = xpos;
    }

    public int getYpos() {

        return ypos;
    }

    public void setYpos(int ypos) {
        if (ypos < 0+10 || ypos+length > Settings.getGirdLenght()-10) {
            return;
        }
        this.ypos = ypos;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    abstract void eat(int howManyPlants,int howManyMeat);
    Interactions currentInteraction;

    void handleMovement(int direction){

        if (isDead) {
            return;
        }
        if (direction == 9) {
            if (whereInMovCircle == 9) {
                whereInMovCircle = 1;
            }
            direction = whereInMovCircle;
            if (currentDely > maxDelay1) {
                currentDely = 0;
                whereInMovCircle++;
            }
            currentDely++;
        }
        if (direction == 10) {
            if (whereInMovCircle == 9) {
                whereInMovCircle = 1;
            }
            direction = whereInMovCircle;
            if (currentDely > maxDelay2) {
                currentDely = 0;
                whereInMovCircle++;
            }
            currentDely++;
        }
        if (direction == 11) {
            if (whereInMovCircle == 9) {
                whereInMovCircle = 1;
            }
            direction = whereInMovCircle;
            if (currentDely > maxDelay3) {
                currentDely = 0;
                whereInMovCircle++;
            }
            currentDely++;
        }
        int[] movData = handleColisions(direction);
        if (movData == null) {
            move(direction);
        }
        else {
            currentInteraction.handel(movData,(Pixies)this);
        }
    }

    private int[] handleColisions(int direction) {
        int plants = 0;
        int meat = 0;
        boolean noInteraction = true;

        Set<Integer> colors = new HashSet<>();
        if (direction == 1) {

            //upwards
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos + i, ypos + length);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }


                }
            }

        } else if (direction == 2) {
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos + 1 + i, ypos + length);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
            for (int i = 0; i < length-1; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos + length, ypos + i + 1);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
        } else if (direction == 3) {
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos + length, ypos + i);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
        } else if (direction == 4) {
            for (int i = 0; i < length-1; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos + 1 + i, ypos - 1);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos + length, ypos + i - 1);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }

        } else if (direction == 5) {
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos + i, ypos - 1);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
        } else if (direction == 6) {
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos - 1 + i, ypos - 1);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
            for (int i = 1; i < length-1; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos - 1, ypos + i );
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
        } else if (direction == 7) {
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos - 1, ypos + i);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
        } else if (direction == 8) {
            for (int i = 0; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos - 1 + i, ypos + length);
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
            for (int i = 1; i < length; i++) {
                int currentCheck = VisualManager.gameBoard.getRGB(xpos - 1, ypos + i );
                if (currentCheck != Color.WHITE.getRGB()) {
                    if (currentCheck == Color.GREEN.getRGB()) {
                        plants++;
                    } else if (currentCheck == Color.RED.getRGB()) {
                        meat++;
                    } else {
                        noInteraction = false;
                        colors.add(currentCheck);
                    }
                }
            }
        }

        if (colors.isEmpty()) {
            if (xpos>10&&ypos>10&&xpos+length<Settings.getGirdLenght()-10&&ypos+length<Settings.getGridHeight()-10) {
                eat(plants,meat);
            }

            return null;
        }
        return colors.stream().mapToInt(Integer::intValue).toArray();
    }
    private void move(int direction){
        if (isDead) {
            return;
        }
        if (direction == 1) {
            //upwards
            if ((ypos + length>= Settings.getGirdLenght()-10)) {
                return;
            }
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);
            setYpos(ypos+1);
            VisualManager.drawSquare(xpos,ypos,length,color);

        } else if (direction == 2) {
            if ((ypos+length >= Settings.getGirdLenght()-10)&&( xpos+length >= Settings.getGirdLenght()-10)) {
                return;
            }
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);
            setYpos(ypos+1);
            setXpos(xpos+1);
            VisualManager.drawSquare(xpos,ypos,length,color);
        } else if (direction == 3) {
            if ( xpos+length > Settings.getGirdLenght()-10){
                return;
            }
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);

            setXpos(xpos+1);
            VisualManager.drawSquare(xpos,ypos,length,color);
        } else if (direction == 4) {
            if ((ypos+length <= 10)&&( xpos+length > Settings.getGirdLenght()-10)) {
                return;
            }
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);
            setYpos(ypos-1);
            setXpos(xpos+1);
            VisualManager.drawSquare(xpos,ypos,length,color);
        } else if (direction == 5) {
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);
            setYpos(ypos-1);

            VisualManager.drawSquare(xpos,ypos,length,color);
        } else if (direction == 6) {
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);
            setYpos(ypos-1);
            setXpos(xpos-1);
            VisualManager.drawSquare(xpos,ypos,length,color);
        } else if (direction == 7) {
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);

            setXpos(xpos-1);
            VisualManager.drawSquare(xpos,ypos,length,color);
        } else if (direction == 8) {
            VisualManager.drawSquare(xpos,ypos,length,Color.WHITE);
            setYpos(ypos+1);
            setXpos(xpos-1);
            VisualManager.drawSquare(xpos,ypos,length,color);
        }

    }
}

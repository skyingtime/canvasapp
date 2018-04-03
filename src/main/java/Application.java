import java.util.Scanner;

/**
 * This is a simple console drawing program without using OO concept and also does not contain enough validations.
 * User should follow the correct command in the description.
 */
public class Application {

    private static char[][] canvas = null;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        outerloop: while(reader.hasNext()) {
            String command = reader.nextLine();
            String[] params = command.split(" ");
            if("Q".equals(params[0])) {
                System.exit(0);
            }
            else if(canvas == null && !"C".equals(params[0])) {
                System.out.println("Canvas has not been initialised, please create it by running C w[Integer] h[Integer].");
                continue outerloop;
            }
            else if("C".equals(params[0]) && params.length == 3) {
                for(int i = 1; i < params.length; i++) {
                    if(!isInteger(params[i])) {
                        System.out.println("Invalid command, it should be C w[Integer] h[Integer].");
                        continue outerloop;
                    }
                }
                Integer width = Integer.parseInt(params[1]);
                Integer height = Integer.parseInt(params[2]);
                canvas = new char[height][width];
            }
            else if("L".equals(params[0]) && params.length == 5) {
                for(int i = 1; i < params.length; i++) {
                    if(!isInteger(params[i])) {
                        System.out.println("Invalid command, it should be L x1[Integer] y1[Integer] x2[Integer] y2[Integer].");
                        continue outerloop;
                    }
                }
                drawLine(params);
            }
            else if("R".equals(params[0]) && params.length == 5) {
                for(int i = 1; i < params.length; i++) {
                    if(!isInteger(params[i])) {
                        System.out.println("Invalid command, it should be R x1[Integer] y1[Integer] x2[Integer] y2[Integer].");
                        continue outerloop;
                    }
                }
                drawRectangle(params);
            }
            else if("B".equals(params[0]) && params.length == 4) {
                for(int i = 1; i < params.length; i++) {
                    if(!isInteger(params[i])) {
                        System.out.println("Invalid command, it should be B x[Integer] y[Integer] c[Integer].");
                        continue outerloop;
                    }
                }
                Integer startX = Integer.parseInt(params[1])-1;
                Integer startY = Integer.parseInt(params[2])-1;
                char c = params[3].charAt(0);
                drawBucketFill(startX, startY, c, canvas[startY][startX]);
            }
            else {
                System.out.println("Invalid command, please check the command description.");
                continue outerloop;
            }

            for(int y = 0; y < canvas.length; y++) {
                for(int x = 0; x < canvas[y].length; x++) {
                    System.out.print(canvas[y][x]);
                }
                System.out.println();
            }

        }
    }

    private static void drawLine(String[] params) {
        Integer startX = Integer.parseInt(params[1])-1;
        Integer startY = Integer.parseInt(params[2])-1;
        Integer endX = Integer.parseInt(params[3])-1;
        Integer endY = Integer.parseInt(params[4])-1;
        if(startX == endX) {
            for(int y = startY; y <= endY; y++) {
                canvas[y][startX] = 'x';
            }
        }
        else if(startY == endY) {
            for(int x = startX; x <= endX; x++) {
                canvas[startY][x] = 'x';
            }
        }

    }

    private static void drawRectangle(String[] params) {
        Integer startX = Integer.parseInt(params[1])-1;
        Integer startY = Integer.parseInt(params[2])-1;
        Integer endX = Integer.parseInt(params[3])-1;
        Integer endY = Integer.parseInt(params[4])-1;
        for(int upper = startX; upper <= endX; upper++) {
            canvas[startY][upper] = 'x';
        }
        for(int right = startY; right <= endY; right++) {
            canvas[right][endX] = 'x';
        }
        for(int left = startY; left <= endY; left++) {
            canvas[left][startX] = 'x';
        }
        for(int lower = startX; lower <= endX; lower++) {
            canvas[endY][lower] = 'x';

        }    }

    private static void drawBucketFill(Integer startX, Integer startY, char fillIn, char cur) {
        if(startX < 0 || startX >= canvas[0].length) {
            return;
        }
        if(startY < 0 || startY >= canvas.length) {
            return;
        }
        if(canvas[startY][startX] == cur) {
            canvas[startY][startX] = fillIn;
        }
        else {
            return;
        }
        drawBucketFill(startX-1, startY-1,  fillIn, cur);
        drawBucketFill(startX, startY-1, fillIn, cur);
        drawBucketFill(startX+1, startY-1, fillIn, cur);
        drawBucketFill(startX-1, startY, fillIn, cur);
        drawBucketFill(startX+1, startY, fillIn, cur);
        drawBucketFill(startX-1, startY+1, fillIn, cur);
        drawBucketFill(startX, startY+1, fillIn, cur);
        drawBucketFill(startX+1, startY+1, fillIn, cur);
    }

    private static boolean isInteger(String val) {
        try {
             Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

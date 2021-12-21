package com.algorithm.recusion;

public class MazeDemo {


    public static void main(String[] args){




        MazeDemo mazeDemo = new MazeDemo();
        int [][]maze = new int[8][9];

        for(int i = 0; i < 9; i++){
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for(int j = 0; j < 8; j++){
            maze[j][0] = 1;
            maze[j][8] = 1;
        }

        mazeDemo.print(maze);











    }

    /**
     *
     * @param maze
     */
    private void print(int [][]maze){

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(maze[j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     *
     * @param maze
     * @param i
     * @param j
     * @return
     */
    public boolean setWay(int[][] maze, int i, int j){

        if(maze[7][8] == 2){
            return true;
        }else{
            if(maze[i][j] == 0){

                maze[i][j] = 2;
                if(setWay(maze, i+1, j)){
                    return true;
                }else if(setWay(maze, i, j+1)){
                    return true;
                }else if(setWay(maze, i-1, j)){
                    return true;
                }else if(setWay(maze, i, j-1)){
                    return true;
                }else{
                    maze[i][j] = 3;
                    return false;
                }

            }else{
                return false;
            }
        }
    }

}

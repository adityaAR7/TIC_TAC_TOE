package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var board: Array<Array<Button>>
    var player: Int = 1
    var count: Int = 0
    var boardstatus = Array(3) { IntArray(3) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        board = arrayOf(
            arrayOf(binding.b1, binding.b2, binding.b3),
            arrayOf(binding.b4, binding.b5, binding.b6),
            arrayOf(binding.b7, binding.b8, binding.b9)
        )
        for (row in board) {
            for (button in row) {
                button.setOnClickListener(this)
            }
        }
        for (i in 0..2) {
            for (j in 0..2)
                boardstatus[i][j] = -1
        }
        binding.reset.setOnClickListener {
            player = 1
            count = 0
            resetAllStates()
        }

    }



    private fun resetAllStates() {
        binding.textview.text="Player X TURN"
        for (row in board) {
            for (button in row) {
                button.text = ""
                button.isEnabled = true
            }
        }
        for (i in 0..2) {
            for (j in 0..2)
                boardstatus[i][j] = -1
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.b1.id -> {
                updateboard(0,0)

            }
            binding.b2.id -> {
                updateboard(0,1)

            }
            binding.b3.id -> {
                updateboard(0,2)

            }
            binding.b4.id -> {
                updateboard(1,0)

            }
            binding.b5.id -> {
                updateboard(1,1)

            }
            binding.b6.id -> {
                updateboard(1,2)

            }
            binding.b7.id -> {
                updateboard(2,0)

            }
            binding.b8.id -> {
                updateboard(2,1)

            }
            binding.b9.id -> {
                updateboard(2,2)
            }
        }
        count++;
        if(count==9){
            display("Game Draw")
        }
        checkWinner()
        player=1-player
    }

    private fun checkWinner() {
        for(i in 0..2){
            if(boardstatus[i][0]!=-1 && (boardstatus[i][0]==boardstatus[i][1]) && (boardstatus[i][1]==boardstatus[i][2])){
                if(boardstatus[i][0]==1)
                    display("Winner is X")
                else
                    display("Winner is Y")
            }
        }
        for(i in 0..2){
            if(boardstatus[0][i]!=-1 && (boardstatus[0][i]==boardstatus[1][i]) && (boardstatus[1][i]==boardstatus[2][i])){
                if(boardstatus[0][i]==1)
                    display("Winner is X")
                else
                    display("Winner is Y")
            }
        }
        if(boardstatus[0][0]!=-1 && boardstatus[0][0]==boardstatus[1][1] && boardstatus[1][1]==boardstatus[2][2]){
            if(boardstatus[0][0]==1)
                display("Winner is X")
            else
                display("Winner is Y")

        }
        if(boardstatus[0][2]!=-1 && boardstatus[0][2]==boardstatus[1][1] && boardstatus[1][1]==boardstatus[2][0]){
            if(boardstatus[0][2]==1)
                display("Winner is X")
            else
                display("Winner is Y")
        }
    }
    private fun display(s: String) {
        if(s.contains("Draw")){
            binding.textview.text=s
        }
        else if(s.contains("Winner")){
            binding.textview.text=s
            for(row in board)
                for(button in row)
                    button.isEnabled=false
        }
    }

    private fun updateboard(row:Int,col:Int) {
        binding.textview.text=if(player==1) "Player Y TURN" else "Player X TURN"
        var txt=if(player==1) "X" else "0"
        boardstatus[row][col]=player
        board[row][col].text=txt
        board[row][col].isEnabled=false
    }
}
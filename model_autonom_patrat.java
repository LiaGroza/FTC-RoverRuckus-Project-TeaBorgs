/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="model_autonom_patrat", group="Linear Opmode")
//@Disabled
public class model_autonom_patrat extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private GoldAlignDetector detector;
    private int pozitie;
    static final int COUNTS = 1112;

    public defRobot robot = new defRobot();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);

        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if(opModeIsActive()) {
            robot.move_front(0.4,860);
            WAIT(false);
/*
            ////////CUB DREAPTA
            robot.move_right(0.3,1050);
            WAIT(false);
            robot.move_front(0.3,620);
            WAIT(false);
            robot.move_back(0.4, 800);
            WAIT(true);
            robot.move_left(0.5,3700);
            WAIT(true);
            ////////////////////////////

            //////////////////////CUB FATA
            robot.move_left(0.4,150);
            WAIT(false);
            robot.move_front(0.3,700);
            WAIT(false);
            robot.move_back(0.4,670);
            WAIT(true);
            robot.move_left(0.5,2890);
            WAIT(true);

            ////////////////////////////////
*/
            /////////////////////CUB STANGA
            robot.move_left(0.2,1150);
            WAIT(false);
            robot.move_front(0.2,870);
            WAIT(false);
            robot.move_back(0.4,720);
            WAIT(true);
            robot.move_left(0.4,1860);
            WAIT(true);


            /////////////////////////////////


            robot.rotate_right(0.2,680);
            WAIT(true);
            robot.move_left(0.3,650);
            WAIT(false);
            robot.move_front(0.9,2900);
            WAIT(true);

            robot.markerServo.setPosition(0.8);
            ///waiting de o sec
            robot.move_back(0.9,3300);
            WAIT(false);

            robot.rotate_left(0.95,1420);
            WAIT(false);
        /*while(opModeIsActive() && robot.isBusy()) {
            telemetry.addData("encoder-fwd-end", robot.frontRight.getCurrentPosition() + "  busy=" + robot.frontLeft.isBusy());
            telemetry.update();

        }
        robot.resetDrives();
        robot.move_left(0.1,10);

        while(opModeIsActive() && robot.isBusy()) {
            telemetry.addData("encoder-fwd-end", robot.frontRight.getCurrentPosition() + "  busy=" + robot.frontLeft.isBusy());
            telemetry.update();

        }
        robot.resetDrives();*/
        }
    }

    public void WAIT(boolean busy){
        if(busy == true)
            while (opModeIsActive() && robot.isBusy()) {

            }

        else
            while(opModeIsActive() && !robot.positionReached() ){

            }

            robot.resetDrives();
    }

}


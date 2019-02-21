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

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="model_autonom", group="Linear Opmode")
//@Disabled
public class model_autonom extends LinearOpMode {

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
            //////////////////////////////////////////////////////////////////

            // Set up detector
            detector = new GoldAlignDetector(); // Create detector
            detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
            detector.useDefaults(); // Set detector to use default settings

            // Optional tuning
            detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
            detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
            detector.downscale = 0.4; // How much to downscale the input frames

            detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
            //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
            detector.maxAreaScorer.weight = 0.005; //

            detector.ratioScorer.weight = 5; //
            detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

            detector.enable(); // Start the detector!
            ///////////////////////////////////////////////////////////
            robot.move_front(1.0,860);
            WAIT(true);
            //////////////////////SAMPLING///////
            if (detector.getXPosition() <= 300 && detector.getXPosition() > 2) pozitie = 2;
            else if (detector.getXPosition() > 300) pozitie = 1;
            else pozitie = 3;

            telemetry.addData("Pozitie:", pozitie);
            telemetry.addData("X:", detector.getXPosition());
            telemetry.update();
            /////////////////////////////////////


            ////////CUB DREAPTA
            /*robot.move_right(1.0,1050);
            WAIT(true);
            robot.move_front(1.0,650);
            WAIT(true);
            robot.move_back(1.0, 830);
            WAIT(true);
            robot.move_left(1.0,3700);
            WAIT(true);*/
            ////////////////////////////

            //////////////////////CUB FATA
            robot.move_left(1.0,150);
            WAIT(true);
            robot.move_front(1.0,800);
            WAIT(true);
            robot.move_back(1.0,770);
            WAIT(true);
            robot.move_left(1.0,2800);
            WAIT(true);

            ////////////////////////////////

            /////////////////////CUB STANGA
            /*robot.move_left(1.0,1150);
            WAIT(true);
            robot.move_front(1.0,870);
            WAIT(true);
            robot.move_back(1.0,720);
            WAIT(true);
            robot.move_left(1.0,1860);
            WAIT(true);*/


            /////////////////////////////////


            robot.rotate_left(0.5,800);
            WAIT(true);
            robot.move_front(0.5,650);
            WAIT(true);
            robot.move_left(0.5,2900);
            WAIT(true);
            robot.rotate_left(0.5, 1330);
            WAIT(true);
            robot.markerServo.setPosition(0.8);
            //WAIT(true);
            robot.markerServo.setPosition(0.0);
            //WAIT(true);
            ///waiting de o sec
            robot.move_back(1.0,3300);
            WAIT(true);

            robot.rotate_left(1.0,1420);
            WAIT(true);
            robot.move_back(1.0,3000);
            WAIT(true);

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

    /*public void WAIT(){
            while(opModeIsActive() && robot.isBusy())
            {

            }

            robot.resetDrives();
    }*/

    public void WAIT(boolean busy){
        if(busy == true)
            while (opModeIsActive() && robot.isBusy()) {

            }

        else
            while(opModeIsActive() && !robot.positionReached()){

            }

            robot.resetDrives();
    }

}


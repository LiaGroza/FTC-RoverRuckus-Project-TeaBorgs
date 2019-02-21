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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;


@Autonomous(name="best_autonom_ever", group="Linear Opmode")
//@Disabled
public class best_autonom extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private GoldAlignDetector detector;
    private int pozitie;

    public definitieRobot robot = new definitieRobot();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        // run until the end of the match (driver presses STOP)
        if(opModeIsActive()) {

            double servoInitPosition = 0.3;
            robot.markerServo.setPosition(servoInitPosition);


            ///LANDING
            robot.latchingLeft.setPower(0.7);
            robot.latchingRight.setPower(0.70);

            while (runtime.seconds() < 1.7);


            robot.lockLeft.setPosition(0.0);
            robot.lockRight.setPosition(1.0);

            while (runtime.seconds() < 3.3) ;

            runtime.reset();


            robot.latchingLeft.setPower(-0.05);
            robot.latchingRight.setPower(-0.05);

            while (runtime.seconds() < 2.0);
            robot.move_front(0.25,1000);

            while(runtime.seconds() < 3);


            runtime.reset();
            while(runtime.seconds() < 1.5)
            {
                robot.move_left(0.2, 100);
            }

            robot.resetDrives();



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
            runtime.reset();
            robot.latchingLeft.setPower(0.5);
            robot.latchingRight.setPower(0.5);
            while (runtime.seconds() < 2.0);


            robot.latchingLeft.setPower(0.0);
            robot.latchingRight.setPower(0.0);

            ///////////////////////////////////
            runtime.reset();

            while(runtime.seconds()<2.0);

            if(detector.getXPosition() <= 300 && detector.getXPosition() > 2) pozitie = 2;
            else if(detector.getXPosition() > 300) pozitie = 1;
            else pozitie = 3;

            telemetry.addData("Pozitie:",pozitie);
            telemetry.update();

       ///CUB1
       ///////////////////
       if(pozitie==1) {
           runtime.reset();
           robot.move_back(0.3, 1);
           while (runtime.seconds() < 1.5) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_left(0.3, 1);
           while (runtime.seconds() < 1.4) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_back(0.3, 1);
           while (runtime.seconds() < 1.0) ;
           robot.resetDrives();
///////////////////////////////////////////////////////

           runtime.reset();
           robot.move_front(0.3, 1);
           while (runtime.seconds() < 1.3) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_right(0.5, 1);
           while (runtime.seconds() < 3.0) ;
           robot.resetDrives();

           runtime.reset();
           robot.rotate_left(0.5, 1);
           while (runtime.seconds() < 1.57) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_left(0.3, 1);
           while (runtime.seconds() < 1.3) ;
           robot.resetDrives();
       }
///////////////////////////////////////////////////////



            //CUB2
//////////////////////////////////////////////////////
            else if (pozitie==2) {
           runtime.reset();
           robot.move_back(0.3, 1);
           while (runtime.seconds() < 1.5) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_right(0.3, 1);
           while (runtime.seconds() < 0.5) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_back(0.3, 1);
           while (runtime.seconds() < 1.0) ;
           robot.resetDrives();
///////////////////////////////////////////////////////

           runtime.reset();
           robot.move_front(0.3, 1);
           while (runtime.seconds() < 1.0) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_right(0.5, 1);
           while (runtime.seconds() < 2.5) ;
           robot.resetDrives();

           runtime.reset();
           robot.rotate_left(0.5, 1);
           while (runtime.seconds() < 1.57) ;
           robot.resetDrives();

           runtime.reset();
           robot.move_left(0.3, 1);
           while (runtime.seconds() < 0.8) ;
           robot.resetDrives();
       }
///////////////////////////////////////////////////////

            //servo
            /*
            robot.markerServo.setPosition(0.2);
            sleep(3000);
            robot.markerServo.setPosition(0.7);
            sleep(3000);
            */


        //CUB3
       ///////////////////
       else{
            runtime.reset();
            robot.move_back(0.3,1);
            while(runtime.seconds() < 1.5);
            robot.resetDrives();

            runtime.reset();
            robot.move_right(0.3,1);
            while(runtime.seconds() < 1.7);
            robot.resetDrives();

            runtime.reset();
            robot.move_back(0.3,1);
            while(runtime.seconds() < 1.0);
            robot.resetDrives();
///////////////////////////////////////////////////////

            runtime.reset();
            robot.move_front(0.3,1);
            while(runtime.seconds() < 0.9);
            robot.resetDrives();

            runtime.reset();
            robot.move_right(0.5,1);
            while(runtime.seconds() < 1.5);
            robot.resetDrives();

            runtime.reset();
            robot.rotate_left(0.5,1);
            while(runtime.seconds() < 1.54);
            robot.resetDrives();

            runtime.reset();
            robot.move_left(0.3,1);
            while(runtime.seconds() < 0.9);
            robot.resetDrives();
       }
///////////////////////////////////////////////////////



            ///MARKER
/////////////////////////////////////////////////////
           runtime.reset();
            robot.move_back(0.6,1);
            while(runtime.seconds() < 2.0);
            robot.resetDrives();

            runtime.reset();
            robot.markerServo.setPosition(1);
            robot.rotate_right(0.6,199);
            while(runtime.seconds() < 0.5);
            robot.resetDrives();
            robot.move_back(0.7,199);
            robot.rotate_left(0.6,199);
            while(runtime.seconds() < 1);

            runtime.reset();

            robot.move_front(0.6,1);
            while(runtime.seconds() < 1.5);
            robot.resetDrives();

            robot.rotate_right(0.1,100);
            while(runtime.seconds() < 2);
            robot.resetDrives();

            robot.move_front(0.6,100);
            while(runtime.seconds() < 3.5);
            robot.resetDrives();

            runtime.reset();
            robot.latchingLeft.setPower(-1);
            robot.latchingRight.setPower(-1);

            while(runtime.seconds() < 1.5);
            robot.latchingLeft.setPower(0);
            robot.latchingRight.setPower(0);


             stop();

            // run until the end of the match (driver presses STOP)
        }
    }
}

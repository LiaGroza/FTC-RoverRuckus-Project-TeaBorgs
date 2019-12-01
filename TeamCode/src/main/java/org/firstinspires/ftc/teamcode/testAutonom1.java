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
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="testAutonom1", group="Linear Opmode")
//@Disabled
public class testAutonom1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private GoldAlignDetector detector;

    definitieRobot robot = new definitieRobot();

    //ConceptTensorFlowObjectDetection detector = new ConceptTensorFlowObjectDetection();

    int mineralResult = 0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.init(hardwareMap);
        robot.markerServo.setPosition(0.3);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        double servoInitPosition = 0.3;
        robot.markerServo.setPosition(servoInitPosition);

        robot.latchingLeft.setPower(0.25);
        robot.latchingRight.setPower(0.25);

        while (runtime.seconds() < 0.5) ;


        robot.lockLeft.setPosition(0.0);
        robot.lockRight.setPosition(1.0);
        while (runtime.seconds() < 0.5) ;

        runtime.reset();
        while (runtime.seconds() < 0.75) {
            robot.latchingLeft.setPower(0.0001);
            robot.latchingRight.setPower(0.0001);
        }
        while (runtime.seconds() < 3.0) {
            robot.latchingLeft.setPower(0.0);
            robot.latchingRight.setPower(0.0);
        }

        runtime.reset();
        while(runtime.seconds() < 1.0)
        {
            robot.move_left(0.3, 100);
        }

        robot.resetDrives();

        runtime.reset();
        while (runtime.seconds() < 2.0) {
            robot.latchingLeft.setPower(0.5);
            robot.latchingRight.setPower(0.5);
        }

        robot.latchingLeft.setPower(0.0);
        robot.latchingRight.setPower(0.0);

        /////////CASE 1 //////////

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
        runtime.reset();
        while (runtime.seconds()<1.0);

        if (detector.getXPosition() >= 50 && detector.getXPosition() <= 360) {
            runtime.reset();
            while (runtime.seconds() < 0.75) {
                robot.move_back(0.5, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 0.85) {
                robot.rotate_left(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 0.5) {
                robot.move_back_right(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 1.75) {
                robot.move_back_left(0.3, 100);
            }
            robot.resetDrives();
            //RETURN
            runtime.reset();
            while (runtime.seconds() < 1.75) {
                robot.move_front_right(0.3, 100);
            }
            robot.resetDrives();
            /*runtime.reset();
            while (runtime.seconds() < 0.25) {
                robot.move_front_left(0.3, 100);
            }*/
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 0.85) {
                robot.rotate_right(0.3, 100);
            }
            robot.resetDrives();
        } else if (detector.getXPosition() > 360) {
            runtime.reset();
            while (runtime.seconds() < 0.75) {
                robot.move_back(0.5, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 0.85) {
                robot.rotate_left(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 1.0) {
                robot.move_front_left(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 1.75) {
                robot.move_back_left(0.3, 100);
            }
            robot.resetDrives();
            //RETURN
            runtime.reset();
            while (runtime.seconds() < 1.75) {
                robot.move_front_right(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 0.5) {
                robot.move_back_right(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 0.85) {
                robot.rotate_right(0.3, 100);
            }
            robot.resetDrives();
        } else {
            runtime.reset();
            while (runtime.seconds() < 0.75) {
                robot.move_back(0.5, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 0.75) {
                robot.rotate_left(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 1.5) {
                robot.move_back_right(0.3, 100);
            }
            robot.resetDrives();
            runtime.reset();
            while (runtime.seconds() < 1.75) {
                robot.move_back_left(0.3, 100);
            }
            robot.resetDrives();
            //RETURN
            runtime.reset();
            while (runtime.seconds() < 1.75) {
                robot.move_front_right(0.3, 100);
            }
            robot.resetDrives();
            /*runtime.reset();
            while (runtime.seconds() < 1.5) {
                robot.move_front_left(0.3, 100);
            }
            robot.resetDrives();*/
            runtime.reset();
            while (runtime.seconds() < 0.75) {
                robot.rotate_right(0.3, 100);
            }
            robot.resetDrives();
        }


        ////////////MARKER
        runtime.reset();
        while(runtime.seconds() < 0.75)
        {
            robot.move_back(0.5, 100);
        }

        runtime.reset();
        while(runtime.seconds() < 2.5)
        {
            robot.rotate_left(0.4, 100);
        }

        runtime.reset();
        while(runtime.seconds() < 2.55)
        {
            robot.move_left(0.5, 100);
        }

        runtime.reset();
        while(runtime.seconds() < 1.1)
        {
            robot.rotate_left(0.3, 100);
        }

        runtime.reset();
        while(runtime.seconds() < 3.0)
        {
            robot.move_left(0.5, 100);
        }
        robot.resetDrives();

        sleep(1000);

        robot.markerServo.setPosition(1.0);
        sleep(1000);

        runtime.reset();
        while(runtime.seconds() < 0.8)
        {
            robot.move_right(0.3, 100);
        }
        robot.resetDrives();
        sleep(1000);
        robot.markerServo.setPosition(0.3);
        /////////////////////////////////////////////////////////////////////


        /*//////////////PARCARE///////////////////////////////////
        runtime.reset();
        while(runtime.seconds() < 3.0)
        {
            robot.move_right(0.6, 100);
        }

        robot.resetDrives();
        sleep(1000);
        runtime.reset();
        while(runtime.seconds() < 4.0)
        {
            robot.move_right(0.3, 100);
        }
        robot.resetDrives();
        //////////////////////////////////////////////////////////*/


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            runtime.reset();
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Mineral Result:", mineralResult);
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}

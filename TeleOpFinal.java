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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOpFinal", group="Linear Opmode")
//@Disabled
public class TeleOpFinal extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime rtLock = new ElapsedTime();

    //omni
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    //latching motor
    public DcMotor latching;
    
    //brat
    public DcMotor arm;
    
    //slider
    public DcMotor slider;

    //cuva
    public DcMotor cup;

    //marker servo
    public Servo markerServo;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        frontLeft  = hardwareMap.get(DcMotor.class, "fl_motor");
        frontRight  = hardwareMap.get(DcMotor.class, "fr_motor");
        backLeft  = hardwareMap.get(DcMotor.class, "bl_motor");
        backRight  = hardwareMap.get(DcMotor.class, "br_motor");
        latching = hardwareMap.get(DcMotor.class, "latching");
        markerServo = hardwareMap.get(Servo.class, "marker_servo");
        arm = hardwareMap.get(DcMotor.class, "brat");
        slider = hardwareMap.get(DcMotor.class, "slider");
        cup = hardwareMap.get(DcMotor.class, "cup");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        latching.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);
        slider.setDirection(DcMotor.Direction.FORWARD);
        cup.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        latching.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        latching.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cup.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        

        double servoInitPosition = 0.4;
        markerServo.setPosition(servoInitPosition);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        rtLock.reset();


        double  omniSupress = 0.3;
        double  armSupress  = 0.3;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            //omni
            double gamepadLeftY = -gamepad2.right_stick_y;
            double gamepadLeftX = -gamepad2.right_stick_x;
            double gamepadRightX = -gamepad2.left_stick_x;

            double powerFrontLeft = -gamepadLeftY - gamepadLeftX - gamepadRightX;
            double powerFrontRight = gamepadLeftY - gamepadLeftX - gamepadRightX;
            double powerBackLeft = -gamepadLeftY + gamepadLeftX - gamepadRightX;
            double powerBackRight = gamepadLeftY + gamepadLeftX - gamepadRightX;

            double powerLatchingUp = -gamepad2.right_trigger;
            double powerLatchingDown = gamepad2.left_trigger;
            
            double powerArmUp = -gamepad1.right_trigger;
            double powerArmDown = gamepad1.left_trigger;
            double powerSlider = gamepad1.left_stick_y;
            double powerCup = gamepad1.right_stick_y;

            powerFrontLeft = Range.clip(powerFrontLeft, -1, 1);
            powerFrontRight = Range.clip(powerFrontRight, -1, 1);
            powerBackLeft = Range.clip(powerBackLeft, -1, 1);
            powerBackRight = Range.clip(powerBackRight, -1, 1);
            powerLatchingUp = Range.clip(powerLatchingUp, -1, 1);
            powerLatchingDown = Range.clip(powerLatchingDown, -1, 1);
            powerArmUp = Range.clip(powerLatchingUp, -1, 1);
            powerArmDown = Range.clip(powerLatchingDown, -1, 1);
            powerSlider = Range.clip(powerLatchingDown, -1, 1);
            powerCup = Range.clip(powerLatchingDown, -1, 1);


            if (gamepad2.right_bumper == true) {
                powerFrontLeft = powerFrontLeft *   omniSupress;
                powerBackLeft = powerBackLeft *     omniSupress;
                powerFrontRight = powerFrontRight * omniSupress;
                powerBackRight = powerBackRight *   omniSupress;

            }

            if (gamepad1.right_bumper == true)
            {
                powerArmUp = powerArmUp * armSupress;
                powerArmDown = powerArmDown * armSupress;
            }

            if (gamepad1.left_bumper == true)
            {
                powerSlider = powerSlider * armSupress;
            }

            frontLeft.setPower(powerFrontLeft);
            frontRight.setPower(powerFrontRight);
            backLeft.setPower(powerBackLeft);
            backRight.setPower(powerBackRight);
            //omni


            //LATCHING - ridicare & coborare
            if(powerLatchingUp !=0 ) latching.setPower(powerLatchingUp);
            else if(powerLatchingDown!=0)latching.setPower(powerLatchingDown);
            else latching.setPower(0);
            

            //ARM
            if(powerArmUp !=0 ) arm.setPower(powerArmUp);
            else if(powerArmDown!=0)arm.setPower(powerArmDown);
            else arm.setPower(0);
            
            //slider
            slider.setPower(powerSlider);

            //cup
            cup.setPower(powerCup);

            // Show the elapsed game time and wheel power.
            //  telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", powerFrontLeft, powerFrontRight);
            telemetry.update();
        }
    }
}

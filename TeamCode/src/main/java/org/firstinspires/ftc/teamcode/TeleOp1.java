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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name="TeleOp1", group="Linear Opmode")
//@Disabled
public class TeleOp1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    //omni
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    //latching motors
    private DcMotor latchingRight;
    private DcMotor latchingLeft;

    //closing servos
    private Servo rightServo;
    private Servo leftServo;

    //cup motor
    private DcMotor rotatingCupMotor;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        frontLeft  = hardwareMap.get(DcMotor.class, "fl_motor");
        frontRight  = hardwareMap.get(DcMotor.class, "fr_motor");
        backLeft  = hardwareMap.get(DcMotor.class, "bl_motor");
        backRight  = hardwareMap.get(DcMotor.class, "br_motor");

        latchingRight = hardwareMap.get(DcMotor.class, "latching_right");
        latchingLeft = hardwareMap.get(DcMotor.class, "latching_left");

        rightServo = hardwareMap.get(Servo.class, "right_servo");
        leftServo = hardwareMap.get(Servo.class, "left_servo");

        rotatingCupMotor = hardwareMap.get(DcMotor.class, "rotating_cup");




        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        latchingRight.setDirection(DcMotor.Direction.FORWARD);
        latchingLeft.setDirection(DcMotor.Direction.REVERSE);

        rotatingCupMotor.setDirection(DcMotor.Direction.FORWARD);




        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            //omni
            double gamepadLeftY  = -gamepad1.left_stick_y;
            double gamepadLeftX  = gamepad1.left_stick_x;
            double gamepadRightX = -gamepad1.right_stick_x;

            double powerFrontLeft = -gamepadLeftY - gamepadLeftX - gamepadRightX;
            double powerFrontRight = gamepadLeftY - gamepadLeftX - gamepadRightX;
            double powerBackLeft = -gamepadLeftY +gamepadLeftX - gamepadRightX;
            double powerBackRight =  gamepadLeftY + gamepadLeftX - gamepadRightX;


            powerFrontLeft = Range.clip(powerFrontLeft, -1, 1);
            powerFrontRight = Range.clip(powerFrontRight, -1, 1);
            powerBackLeft = Range.clip(powerBackLeft, -1, 1);
            powerBackRight = Range.clip(powerBackRight, -1, 1);

            frontLeft.setPower(powerFrontLeft);
            frontRight.setPower(powerFrontRight);
            backLeft.setPower(powerBackLeft);
            backRight.setPower(powerBackRight);
            //omni



            //LATCHING - ridicare & coborare
            double upLatch = gamepad1.right_trigger;
            double downLatch = gamepad1.left_trigger;

            if (upLatch != 0.0) {
                latchingRight.setPower(1.0);
                latchingLeft.setPower(1.0);
            } else {
                latchingRight.setPower(0.0);
                latchingLeft.setPower(0.0);
            }

            if (downLatch != 0.0) {
                latchingRight.setPower(-0.5);
                latchingLeft.setPower(-0.5);
            } else {
                latchingRight.setPower(0.0);
                latchingLeft.setPower(0.0);
            }
            //LATCHING - ridicare & coborare



            //LOCKING & UNLOCKING
            boolean upLock = gamepad1.right_bumper;
            boolean downLock = gamepad1.left_bumper;

            if (upLock) {
                rightServo.setPosition(0.0);
                leftServo.setPosition(1.0);
            }

            if (downLock) {
                rightServo.setPosition(1.0);
                leftServo.setPosition(0.0);
            }
            //LOCKING & UNLOCKING



            //ROTATING CUP
            boolean upCup = gamepad1.dpad_up;
            boolean downCup = gamepad1.dpad_down;

            if (upCup) {
                rotatingCupMotor.setPower(0.5);
            } else {
                rotatingCupMotor.setPower(0.0);
            }

            if (downCup) {
                rotatingCupMotor.setPower(-0.5);
            } else {
                rotatingCupMotor.setPower(0.0);
            }
            //ROTATING CUP




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", powerFrontLeft, powerFrontRight);
            telemetry.update();
        }
    }
}

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



@TeleOp(name="TeleOp1", group="Linear Opmode")
//@Disabled
public class TeleOp1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime rtLock = new ElapsedTime();

    //omni
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    //latching motors
    public DcMotor latchingRight;
    public DcMotor latchingLeft;

    //closing servos
    public Servo rightServo;
    public Servo leftServo;

    public CRServo servoCuva;

    //cup motor
    //
    public DcMotor rotatingCupMotor;

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

        latchingRight = hardwareMap.get(DcMotor.class, "latching_right");
        latchingLeft = hardwareMap.get(DcMotor.class, "latching_left");

        rightServo = hardwareMap.get(Servo.class, "right_servo");
        leftServo = hardwareMap.get(Servo.class, "left_servo");

        rotatingCupMotor = hardwareMap.get(DcMotor.class, "rotating_cup");
        servoCuva       =hardwareMap.get(CRServo.class,"cup_servo");

        markerServo = hardwareMap.get(Servo.class, "marker_servo");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        latchingRight.setDirection(DcMotor.Direction.FORWARD);
        latchingLeft.setDirection(DcMotor.Direction.FORWARD);

        rotatingCupMotor.setDirection(DcMotor.Direction.FORWARD);


        boolean locked = false;

        double servoInitPosition = 0.4;
        markerServo.setPosition(servoInitPosition);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        rtLock.reset();


        double  omniSurpress = 0.3;
        double  cupSupress   = 0.2;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            rotatingCupMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //latchingRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //latchingLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //omni
            double gamepadLeftY = -gamepad2.right_stick_y;
            double gamepadLeftX = -gamepad2.right_stick_x;
            double gamepadRightX = -gamepad2.left_stick_x;

            double powerFrontLeft = -gamepadLeftY - gamepadLeftX - gamepadRightX;
            double powerFrontRight = gamepadLeftY - gamepadLeftX - gamepadRightX;
            double powerBackLeft = -gamepadLeftY + gamepadLeftX - gamepadRightX;
            double powerBackRight = gamepadLeftY + gamepadLeftX - gamepadRightX;

            double powerLatchingUp = -gamepad1.right_trigger;
            double powerLatchingDown = gamepad1.left_trigger;

            powerFrontLeft = Range.clip(powerFrontLeft, -1, 1);
            powerFrontRight = Range.clip(powerFrontRight, -1, 1);
            powerBackLeft = Range.clip(powerBackLeft, -1, 1);
            powerBackRight = Range.clip(powerBackRight, -1, 1);

            powerLatchingUp = Range.clip(powerLatchingUp, -1, 1);
            powerLatchingDown = Range.clip(powerLatchingDown, -1, 1);



            if (gamepad2.right_bumper == true) {
                powerFrontLeft = powerFrontLeft *   omniSurpress;
                powerBackLeft = powerBackLeft *     omniSurpress;
                powerFrontRight = powerFrontRight * omniSurpress;
                powerBackRight = powerBackRight *   omniSurpress;

            }

            if (gamepad1.left_bumper == true)
            {
                powerLatchingUp = powerLatchingUp * omniSurpress;
                powerLatchingDown = powerLatchingDown * omniSurpress;
            }

            frontLeft.setPower(powerFrontLeft);
            frontRight.setPower(powerFrontRight);
            backLeft.setPower(powerBackLeft);
            backRight.setPower(powerBackRight);
            //omni


            //LATCHING - ridicare & coborare

            latchingRight.setPower(powerLatchingUp);
            latchingLeft.setPower(-powerLatchingUp);


            latchingRight.setPower(powerLatchingDown);
            latchingLeft.setPower(-powerLatchingDown);


            //LOCKING & UNLOCKING


            if (gamepad1.x) {

                if (rtLock.seconds() > 0.7) {

                    if (locked) {
                        rightServo.setPosition(1.0);
                        leftServo.setPosition(0.0);
                        locked = false;
                    } else {
                        rightServo.setPosition(0.0);
                        leftServo.setPosition(1.0);
                        locked = true;
                    }
                    rtLock.reset();
                }

            }


            //CUP SERVO
            //////////////////////////////////////////////

            if(gamepad2.right_trigger != 0)
            {
                servoCuva.setPower(1);
            }
            else if(gamepad2.left_trigger != 0)
            {
                servoCuva.setPower(-1);
            }
            else servoCuva.setPower(0);


            //////////////////////////////////////////////
            //ROTATING CUP
            double powerCup = gamepad1.right_stick_y;

            powerCup = Range.clip(powerCup, -0.4, 0.4);

            if (gamepad1.right_bumper == true) {

                powerCup = powerCup * cupSupress;
            }

            rotatingCupMotor.setPower(powerCup);


            // Show the elapsed game time and wheel power.
            //  telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", powerFrontLeft, powerFrontRight);
            telemetry.update();
        }
    }
}

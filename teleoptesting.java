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
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="teleoptesting", group="Linear Opmode")
//@Disabled
public class teleoptesting extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime rtLock = new ElapsedTime();

    //omni
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    //latching motors
    public DcMotor latching;

    //marker servo
    public Servo markerServo;

    boolean isUp;
    double power = 0.2;
   ;
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

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        latching.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        latching.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        latching.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        latching.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        latching.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //////marker


        isUp =true;
        markerServo.setPosition(0);
        while(opModeIsActive())
        {


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


            if (powerFrontLeft<0)  powerFrontLeft=-0.2;
            else if (powerFrontLeft>0)powerFrontLeft=0.2;
            else powerFrontLeft = 0;

            if (powerFrontRight<0)  powerFrontRight=-0.2;
            else if(powerFrontRight>0)powerFrontRight=0.2;
            else powerFrontRight=0;

            if (powerBackLeft<0)  powerBackLeft=-0.2;
            else if(powerBackLeft>0)powerBackLeft=0.2;
            else powerBackLeft =0;

            if (powerBackRight<0)  powerBackRight=-0.2;
            else if(powerBackRight>0)powerBackRight=0.2;
            else powerBackRight=0;


            if(powerLatchingUp !=0 ) latching.setPower(powerLatchingUp);
            else if(powerLatchingDown!=0)latching.setPower(powerLatchingDown);
            else latching.setPower(0);

            if(gamepad2.y){
                if(rtLock.seconds() > 0.7) {
                    if (isUp == true) {
                        isUp = false;
                        markerServo.setPosition(0.8);
                    } else {
                        isUp = true;
                        markerServo.setPosition(0);
                    }
                    rtLock.reset();
                }

            }

            if(gamepadRightX != 0) {
                frontLeft.setPower(powerFrontLeft);
                frontRight.setPower(powerFrontRight);
                backLeft.setPower(powerBackLeft);
                backRight.setPower(powerBackRight);
            }

            ////////////////////////////////////////////
                 if (gamepad2.dpad_down) {
                    frontLeft.setPower(power);
                    frontRight.setPower(power);
                    backLeft.setPower(power);
                    backRight.setPower(power);
                } else if (gamepad2.dpad_up) {
                    frontLeft.setPower(-power);
                    frontRight.setPower(-power);
                    backLeft.setPower(-power);
                    backRight.setPower(-power);
                } else if (gamepad2.dpad_right) {
                    frontLeft.setPower(-power);
                    frontRight.setPower(power);
                    backLeft.setPower(power);
                    backRight.setPower(-power);
                } else if (gamepad2.dpad_left) {
                    frontLeft.setPower(power);
                    frontRight.setPower(-power);
                    backLeft.setPower(-power);
                    backRight.setPower(power);
                } else if(gamepad2.right_bumper)  {
                     frontLeft.setPower(-power);
                     frontRight.setPower(power);
                     backLeft.setPower(-power);
                     backRight.setPower(power);
                 } else if(gamepad2.left_bumper) {
                     frontLeft.setPower(power);
                     frontRight.setPower(-power);
                     backLeft.setPower(power);
                     backRight.setPower(-power);
                 }
                else {
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    backLeft.setPower(0);
                    backRight.setPower(0);
                }

            if(gamepad2.x){
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                latching.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                latching.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            }

            telemetry.addData("frontLeft",frontLeft.getCurrentPosition());
            telemetry.addData("frontRight",frontRight.getCurrentPosition());
            telemetry.addData("backLeft",backLeft.getCurrentPosition());
            telemetry.addData("backRight",backRight.getCurrentPosition());
            telemetry.addData("latching",latching.getCurrentPosition());
            telemetry.update();




        }
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            latching.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
}

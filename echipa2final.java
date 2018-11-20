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
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="echipa2", group="Linear Opmode")
//@Disabled
public class echipa2final extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //latching motors
    private DcMotor latchingRight;
    private DcMotor latchingLeft;

    //omni wheels motors
    private DcMotor forwardLeftMotor;
    private DcMotor forwardRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    //closing servos
    private Servo rightServo;
    private Servo leftServo;

    //cup motor
    private DcMotor rotatingCupMotor;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables -- for pin, lift & omni wheels --. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        latchingRight = hardwareMap.get(DcMotor.class, "latching_right");
        latchingLeft = hardwareMap.get(DcMotor.class, "latching_left");

        forwardLeftMotor = hardwareMap.get(DcMotor.class, "fl_motor");
        forwardRightMotor = hardwareMap.get(DcMotor.class, "fr_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "bl_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "br_motor");

        rightServo = hardwareMap.get(Servo.class, "right_servo");
        leftServo = hardwareMap.get(Servo.class, "left_servo");

        rotatingCupMotor = hardwareMap.get(DcMotor.class, "rotating_cup");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        forwardLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        forwardRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        latchingRight.setDirection(DcMotor.Direction.FORWARD);
        latchingLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;

            //omni wheels moving in POV mode -- left stick for moving forward , right stick for turning
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);

            /*forwardLeftMotor.setPower(leftPower);
            forwardRightMotor.setPower(rightPower);
            backLeftMotor.setPower(leftPower);
            backRightMotor.setPower(rightPower);*/

            /*latchingRight.setPower(0.1);
            latchingLeft.setPower(0.1);*/

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

            //OMNI WHEELS
            //moving forwards and backwards
            double forwardWheels = gamepad1.right_stick_y;

            if (forwardWheels < -0.5) {
                forwardLeftMotor.setPower(0.5);
                forwardRightMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(0.5);
            } else if (forwardWheels > 0.5) {
                forwardLeftMotor.setPower(-0.5);
                forwardRightMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                backRightMotor.setPower(-0.5);
            } else {
                forwardLeftMotor.setPower(0.0);
                forwardRightMotor.setPower(0.0);
                backLeftMotor.setPower(0.0);
                backRightMotor.setPower(0.0);
            }

            //turning left and right
            double rotatingWheels = gamepad1.left_stick_x;

            if (rotatingWheels < -0.5) {
                forwardLeftMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                forwardLeftMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
            } else if (rotatingWheels > 0.5) {
                forwardRightMotor.setPower(-0.5);
                backRightMotor.setPower(-0.5);
                forwardRightMotor.setPower(0.5);
                backRightMotor.setPower(0.5);
            } else {
                forwardLeftMotor.setPower(0.0);
                forwardRightMotor.setPower(0.0);
                backLeftMotor.setPower(0.0);
                backRightMotor.setPower(0.0);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
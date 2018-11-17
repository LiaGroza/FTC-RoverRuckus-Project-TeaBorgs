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

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class robotTeleOp
{
    /* Public OpMode members. */
    public DcMotor frontLeft    = null;
    public DcMotor frontRight   = null;
    public DcMotor backLeft     = null;
    public DcMotor backRight    = null;
    public DcMotor latchingLeft = null;
    public DcMotor latchingRight = null;
    public DcMotor cup = null;

    public Servo lockLeft    = null;
    public Servo lockRight    = null;
    public Servo marker    = null;
    //////////////////////////////////////////////////////

    /* local OpMode members. */
    public static HardwareMap hwMap ;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public robotTeleOp(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft  = hwMap.get(DcMotor.class, "fl_motor");
        frontRight  = hwMap.get(DcMotor.class, "fr_motor");
        backLeft  = hwMap.get(DcMotor.class, "bl_motor");
        backRight = hwMap.get(DcMotor.class, "br_motor");
        latchingLeft  = hwMap.get(DcMotor.class, "");
        latchingRight  = hwMap.get(DcMotor.class, "frontLeft");
        cup  = hwMap.get(DcMotor.class, "rotating_cup");
        lockLeft  = hwMap.get(Servo.class, "latching_left");
        lockRight  = hwMap.get(Servo.class, "latching_right");
        marker  = hwMap.get(Servo.class, "marker");



        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        latchingLeft.setDirection(DcMotor.Direction.FORWARD);
        latchingRight.setDirection(DcMotor.Direction.REVERSE);
        cup.setDirection(DcMotor.Direction.FORWARD);



        frontLeft.setPower(0.1);



        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and initialize ALL installed servos.
        // front  = hwMap.get(Servo.class, "left_hand");
        //rightClaw = hwMap.get(Servo.class, "right_hand");

    }
 }


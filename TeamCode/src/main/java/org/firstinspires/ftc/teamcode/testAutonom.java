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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="testAutonom", group="Iterative Opmode")
//@Disabled
public class testAutonom extends OpMode
{
    //HardwareMap hwMap           =  null;

    private ElapsedTime runtime = new ElapsedTime();

    robotAutonom robot = new robotAutonom();
    @Override
    public void init() {

        robot.init(hardwareMap);
    }


    @Override
    public void init_loop() {
    }


    @Override
    public void start() {
        runtime.reset();
        robot.latchingLeft.setPower(0.25);
        robot.latchingRight.setPower(0.25);

        while (runtime.seconds() < 0.8) ;


        robot.lockLeft.setPosition(0.0);
        robot.lockRight.setPosition(1.0);
        while (runtime.seconds() < 1.2) ;
            /*robot.latchingLeft.setPower(0.1);
            robot.latchingRight.setPower(0.1);
            if(runtime.seconds()==3)
            {
                robot.latchingLeft.setPower(0.0);
                robot.latchingRight.setPower(0.0);
            }*/
        runtime.reset();
        while (runtime.seconds() < 0.75) {
            robot.latchingLeft.setPower(0.0001);
            robot.latchingRight.setPower(0.0001);
        }
        while (runtime.seconds() < 3.0) {
            robot.latchingLeft.setPower(0.0);
            robot.latchingRight.setPower(0.0);
        }

    }


    @Override
    public void loop() {

    }


    @Override
    public void stop() {
    }

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name="TestGyroDir", group="Pushbot")
public class TestGyroDir extends LinearOpMode {

    float rotate_angle = 0;
    double reset_angle = 0;

    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontRight = null;

    BNO055IMU imu;
    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.dcMotor.get("fl_motor");
        backLeft = hardwareMap.dcMotor.get("bl_motor");
        backRight = hardwareMap.dcMotor.get("br_motor");
        frontRight = hardwareMap.dcMotor.get("fr_motor");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        imu.initialize(parameters);

        while(!opModeIsActive()){}

        while(opModeIsActive()){
            drive();
            resetAngle();
            //driveSimple();
            telemetry.update();
        }
    }
    public void driveSimple(){
        double power = .5;
        if(gamepad1.dpad_up){ //Forward
            frontLeft.setPower(-power);
            backLeft.setPower(-power);
            backRight.setPower(-power);
            frontRight.setPower(-power);
        }
        else if(gamepad1.dpad_left){ //Left
            frontLeft.setPower(power);
            backLeft.setPower(-power);
            backRight.setPower(power);
            frontRight.setPower(-power);
        }
        else if(gamepad1.dpad_down){ //Back
            frontLeft.setPower(power);
            backLeft.setPower(power);
            backRight.setPower(power);
            frontRight.setPower(power);
        }
        else if(gamepad1.dpad_right){ //Right
            frontLeft.setPower(-power);
            backLeft.setPower(power);
            backRight.setPower(-power);
            frontRight.setPower(power);
        }
        else if(Math.abs(gamepad1.right_stick_x) > 0){ //Rotation
            frontLeft.setPower(-gamepad1.right_stick_x);
            backLeft.setPower(-gamepad1.right_stick_x);
            backRight.setPower(gamepad1.right_stick_x);
            frontRight.setPower(gamepad1.right_stick_x);
        }
        else{
            frontLeft.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }
    }
    public void drive() {
        double Protate =0;
        double stick_x = 0;
        double stick_y = 0;
        double theta = 0;
        double Px = 0;
        double Py = 0;

        double gyroAngle = getHeading() * Math.PI / 180; //Converts gyroAngle into radians
        if (gyroAngle <= 0) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (0 < gyroAngle && gyroAngle < Math.PI / 2) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (Math.PI / 2 <= gyroAngle) {
            gyroAngle = gyroAngle - (3 * Math.PI / 2);
        }
        gyroAngle = -1 * gyroAngle;

     /*   if(gamepad1.right_bumper){
            gyroAngle = -Math.PI/2;
        }
        */
        if(gamepad1.dpad_right){
            stick_x = 0.5;
        }
        else if(gamepad1.dpad_left){
            stick_x = -0.5;
        }
        if(gamepad1.dpad_up){
            stick_y = -0.5;
        }
        else if(gamepad1.dpad_down){
            stick_y = 0.5;
        }

        if(gamepad1.left_bumper){
            Protate = - 0.4;
        }
        else if(gamepad1.right_bumper){
            Protate = 0.4;
        }

        //MOVEMENT
        theta = Math.atan2(stick_y, stick_x) - gyroAngle - (Math.PI / 2);
        Px = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta + Math.PI / 4));
        Py = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta - Math.PI / 4));


        frontLeft.setPower(Py - Protate);
        backLeft.setPower(Px - Protate);
        backRight.setPower(Py + Protate);
        frontRight.setPower(Px + Protate);
    }
    public void resetAngle(){
        if(gamepad1.a){
            reset_angle = getHeading() + reset_angle;
        }
    }
    public double getHeading(){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double heading = angles.firstAngle;
        if(heading < -180) {
            heading = heading + 360;
        }
        else if(heading > 180){
            heading = heading - 360;
        }
        heading = heading - reset_angle;
        return heading;
    }
}

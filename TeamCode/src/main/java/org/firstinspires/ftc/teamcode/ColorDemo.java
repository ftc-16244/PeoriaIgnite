package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ColorDemo extends LinearOpMode {
    double redContrast;
    double blueContrast;

    @Override
    public void runOpMode() {
        RevColorSensorV3 sensor = hardwareMap.get(RevColorSensorV3.class, "color");

        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            redContrast = sensor.red() / ((double) (sensor.green() + sensor.blue()));
            blueContrast = sensor.blue() / ((double) (sensor.red() + sensor.green()));

            if(redContrast > blueContrast && redContrast > 0.5f) {
                // red sample
                motor.setPower(0.1);
            } else if(blueContrast > redContrast && blueContrast > 0.5f) {
                // blue sample
                motor.setPower(-0.1);
            } else {
                // yellow sample
                motor.setPower(0.1);
            }

            telemetry.addData("Red contrast", redContrast);
            telemetry.addData("Blue contrast", blueContrast);
            telemetry.update();
        }
    }
}

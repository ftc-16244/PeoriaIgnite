package org.firstinspires.ftc.teamcode.Lift;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ColorLift extends LinearOpMode {
    double redContrast;
    double blueContrast;

    @Override
    public void runOpMode() {
        RevColorSensorV3 sensor = hardwareMap.get(RevColorSensorV3.class, "color");
        Lift lift = new Lift(hardwareMap);

        waitForStart();

        lift.slideMechanicalReset();

        while (opModeIsActive()) {
            redContrast = sensor.red() / ((double) (sensor.green() + sensor.blue()));
            blueContrast = sensor.blue() / ((double) (sensor.red() + sensor.green()));

            if(redContrast > blueContrast && redContrast > 1.0f) {
                // red sample
                lift.setSlideLevel3();
            } else if(blueContrast > redContrast && blueContrast > 1.0f) {
                // blue sample
                lift.setSlideLevel1();
            } else if(redContrast > 0.4) {
                // yellow sample
                lift.setSlideLevel2();
            } else {
                lift.setSlideLevel0();
            }

            telemetry.addData("Red contrast", redContrast);
            telemetry.addData("Blue contrast", blueContrast);
            telemetry.update();
            sleep(100);
        }
    }
}

package org.firstinspires.ftc.teamcode.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HWMaps.Robot;
import org.firstinspires.ftc.teamcode.Lib.MotorEnhanced;

public class TurnToHeading extends Action {
    private Robot robot = Robot.getInstance();
    private double heading;
    private double errorThreshold = 2.5;

    public TurnToHeading(double heading) {
        this.heading = heading;
    }

    @Override
    public void init() {
        MotorEnhanced.setRunMode(robot.drive.getAllMotors(), DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void run() {
        if (!actionIsComplete) {
            while (Math.abs(robot.sensors.integrateHeading(heading - robot.sensors.getHeading())) > errorThreshold) {
                robot.turnToHeadingWithoutLoop(heading);
            }
        }
    }

    @Override
    public void kill() {
        actionIsComplete = true;
        robot.drive.setPowers(0, 0);
    }
}

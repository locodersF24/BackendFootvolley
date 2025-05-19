package org.example.backendfootvolley.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PointsAtStake {

    TYPE_1000(1000, 600, 400, 300, 180, 90, 40),
    TYPE_500(500, 300, 200, 150, 90, 40, 20),
    TYPE_100(100, 60, 40, 30, 18, 9, 4);

    public final int winner;
    public final int finalist;
    public final int third;
    public final int fourth;
    public final int quarterFinal;
    public final int r16;
    public final int groupOther;

    public static PointsAtStake parse(int value) {
        for (PointsAtStake pointsAtStake : PointsAtStake.values()) {
            if (pointsAtStake.winner == value) {
                return pointsAtStake;
            }
        }
        throw new IllegalArgumentException("" + value);
    }

}

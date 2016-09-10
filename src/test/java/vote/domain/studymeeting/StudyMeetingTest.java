package vote.domain.studymeeting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vote.util.DateUtil;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static vote.test.TestConstants.*;

public class StudyMeetingTest {

    private StudyMeeting studyMeeting;

    @Before
    public void setUp() throws Exception {
        studyMeeting = new StudyMeeting(TITLE1, SUMMARY1);
    }

    @Test
    public void 勉強会を作成した直後の参加希望者は0人() throws Exception {
        // exercise
        NumberOfWishing numberOfWishing = studyMeeting.getNumberOfWishing();

        // verify
        assertThat(numberOfWishing).isEqualTo(new NumberOfWishing(0));
    }

    @Test
    public void 参加を希望したら_参加希望者数が1加算される() throws Exception {
        // exercise
        studyMeeting.add(USER1);

        // verify
        NumberOfWishing numberOfWishing = studyMeeting.getNumberOfWishing();
        assertThat(numberOfWishing).isEqualTo(new NumberOfWishing(1));
    }

    @Test
    public void 参加希望したユーザーの情報が参加希望として追加される() throws Exception {
        // exercise
        studyMeeting.add(USER1);

        // verify
        ParticipateWishing participateWishing = studyMeeting.getLastAddedWishing();

        assertThat(participateWishing.getUser()).isEqualTo(USER1);
    }

    @Test
    public void 参加希望の登録日時には現在日時が設定されていること() throws Exception {
        // setup
        DateUtil.fixeNow(2016, 3, 2, 12, 14, 21);

        // exercise
        studyMeeting.add(USER1);

        // verify
        ParticipateWishing participateWishing = studyMeeting.getLastAddedWishing();

        assertThat(participateWishing.getRegisterDateTime()).isEqualTo(RegisterDateTime.now());
    }

    @Test
    public void 既に参加しているユーザーが参加希望すると例外がスローされる() throws Exception {
        // setup
        studyMeeting.add(USER1);

        // exercise
        assertThatThrownBy(() -> studyMeeting.add(USER1))
                .isInstanceOf(IllegalParticipateStudyMeetingException.class)
                .hasMessage("指定されたユーザーは既に参加を希望しています（User = " + USER1 + "）");
    }

    @Test
    public void 参加希望が0の状態で最新の参加希望登録日時を取得すると空のOptionalが返る() throws Exception {
        // exercise
        Optional<RegisterDateTime> actual = studyMeeting.getMaximumRegisterDateTimeOfWishing();

        // verify
        assertThat(actual).isEmpty();
    }

    @Test
    public void 最新の参加希望の登録日時を取得できる() throws Exception {
        // setup
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 0);
        studyMeeting.add(USER1);

        DateUtil.fixeNow(2016, 1, 1, 0, 0, 1);
        studyMeeting.add(USER2);

        DateUtil.fixeNow(2016, 1, 1, 0, 0, 2);
        studyMeeting.add(USER3);

        DateUtil.resetNow();

        // exercise
        Optional<RegisterDateTime> actual = studyMeeting.getMaximumRegisterDateTimeOfWishing();

        // verify
        RegisterDateTime expected = new RegisterDateTime(LocalDateTime.of(2016, 1, 1, 0, 0, 2));
        assertThat(actual).hasValue(expected);
    }

    @Test
    public void 参加者であるかどうか確認できる_未参加の場合() throws Exception {
        // exercise
        boolean actual = studyMeeting.isParticipatedBy(USER1);

        // verify
        assertThat(actual).isFalse();
    }

    @Test
    public void 参加者であるかどうか確認できる_参加の場合() throws Exception {
        // setup
        studyMeeting.add(USER2);

        // exercise
        boolean actual = studyMeeting.isParticipatedBy(USER2);

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void 参加希望をキャンセルできる() throws Exception {
        // setup
        studyMeeting.add(USER1);

        // exercise
        studyMeeting.cancel(USER1);

        // verify
        assertThat(studyMeeting.isParticipatedBy(USER1)).isFalse();
    }

    @Test
    public void 参加希望を出していないユーザーがキャンセルをしようとした場合は例外がスローされる() throws Exception {
        // exercise
        assertThatThrownBy(() -> studyMeeting.cancel(USER1))
            .isInstanceOf(IllegalParticipateStudyMeetingException.class)
            .hasMessage("指定されたユーザーは参加希望を出していません（User=" + USER1 + "）");
    }

    @Test
    public void 生成直後は未実施() throws Exception {
        // exercise
        boolean actual = studyMeeting.isCompleted();

        // verify
        assertThat(actual).isFalse();
    }

    @Test
    public void 実施済みに変更できる() throws Exception {
        // exercise
        studyMeeting.complete();

        // verify
        boolean actual = studyMeeting.isCompleted();
        assertThat(actual).isTrue();
    }

    @Test
    public void 未実施に戻すこともできる() throws Exception {
        // setup
        studyMeeting.complete();

        // exercise
        studyMeeting.reopen();

        // verify
        boolean actual = studyMeeting.isCompleted();
        assertThat(actual).isFalse();
    }

    @After
    public void tearDown() throws Exception {
        DateUtil.resetNow();
    }
}
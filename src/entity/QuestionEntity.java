package entity;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Question", schema = "dbo", catalog = "OnlineTest")
public class QuestionEntity
{
    private int questionId;
    private int courseId;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;
    private BigDecimal score;
    private String stem;

    public QuestionEntity(int courseId, String a, String b, String c, String d, String answer, BigDecimal score, String stem)
    {
        this.courseId = courseId;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.score = score;
        this.stem = stem;
    }

    public QuestionEntity(int questionId, int courseId, String a, String b, String c, String d, String answer, BigDecimal score, String stem)
    {
        this.questionId = questionId;
        this.courseId = courseId;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.score = score;
        this.stem = stem;
    }

    public QuestionEntity()
    {
    }

    @Id
    @Column(name = "QuestionID", nullable = false)
    public int getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "CourseID", nullable = false)
    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "A", nullable = false, length = 200)
    public String getA()
    {
        return a;
    }

    public void setA(String a)
    {
        this.a = a;
    }

    @Basic
    @Column(name = "B", nullable = false, length = 200)
    public String getB()
    {
        return b;
    }

    public void setB(String b)
    {
        this.b = b;
    }

    @Basic
    @Column(name = "C", nullable = false, length = 200)
    public String getC()
    {
        return c;
    }

    public void setC(String c)
    {
        this.c = c;
    }

    @Basic
    @Column(name = "D", nullable = false, length = 200)
    public String getD()
    {
        return d;
    }

    public void setD(String d)
    {
        this.d = d;
    }

    @Basic
    @Column(name = "Answer", nullable = false, length = 1)
    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    @Basic
    @Column(name = "Score", nullable = false, precision = 2)
    public BigDecimal getScore()
    {
        return score;
    }

    public void setScore(BigDecimal score)
    {
        this.score = score;
    }

    @Basic
    @Column(name = "Stem", nullable = false, length = 200)
    public String getStem()
    {
        return stem;
    }

    public void setStem(String stem)
    {
        this.stem = stem;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (questionId != that.questionId) return false;
        if (courseId != that.courseId) return false;
        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        if (b != null ? !b.equals(that.b) : that.b != null) return false;
        if (c != null ? !c.equals(that.c) : that.c != null) return false;
        if (d != null ? !d.equals(that.d) : that.d != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (stem != null ? !stem.equals(that.stem) : that.stem != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = questionId;
        result = 31 * result + courseId;
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (stem != null ? stem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "QuestionEntity{" +
                "questionId=" + questionId +
                ", courseId=" + courseId +
                ", stem='" + stem + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                '}';
    }
}

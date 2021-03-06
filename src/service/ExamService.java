package service;

import java.util.ArrayList;
import java.util.List;

import dao.ExamDAO;
import dao.ExamLogDAO;
import dao.QuestionDAO;
import dao.ReportDAO;
import dao.UserAnswerDAO;
import entity.CourseEntity;
import entity.ExamEntity;
import entity.ExamLogEntity;
import entity.QuestionEntity;
import entity.ReportEntity;
import entity.StudentEntity;
import entity.UserAnswerEntity;
import util.Algorithm;
import util.HibernateUtil;

public class ExamService
{
    private ExamDAO examDAO = new ExamDAO();
    private UserAnswerDAO userAnswerDAO = new UserAnswerDAO();
    private QuestionDAO questionDAO = new QuestionDAO();
    private ExamLogDAO examLogDAO = new ExamLogDAO();
    private ReportDAO reportDAO = new ReportDAO();

    /**
     * 添加考试
     *
     * @param examEntity 考试实体
     * @return Integer
     */
    public Integer addExam(ExamEntity examEntity)
    {
        return examDAO.addExam(examEntity);
    }

    /**
     * 删除考试
     *
     * @param examEntity 考试实体
     */
    public boolean deleteExam(ExamEntity examEntity)
    {
        return examDAO.deleteExam(examEntity);
    }

    /**
     * 修改考试
     *
     * @param examEntity 考试实体
     */
    public boolean modifyExam(ExamEntity examEntity)
    {
        return examDAO.modifyExam(examEntity);
    }

    /**
     * 查询所有考试
     *
     * @return List<ExamEntity>
     */
    public List<ExamEntity> queryAllExams()
    {
        return examDAO.queryAllExams();
    }

    /**
     * 根据名称查询考试列表
     *
     * @param courseEntity 考试实体
     * @return List<ExamEntity>
     */
    public List<ExamEntity> queryExamsByCourse(CourseEntity courseEntity)
    {
        return examDAO.queryExamsByCourse(courseEntity);
    }

    /**
     * 根据学生查询可考列表
     *
     * @param studentEntity 学生实体
     * @return List<ExamEntity>
     */
    public List<ExamEntity> queryExamsByStudent(StudentEntity studentEntity)
    {
        return examDAO.queryExamsByStudent(studentEntity);
    }

    /**
     * 考试答题
     *
     * @param userAnswerEntity 考试答题实体
     * @return boolean true-答题成功 false-答题失败
     */
    public boolean answerQuestion(UserAnswerEntity userAnswerEntity)
    {
        try
        {
            int userAnswerID = userAnswerDAO.getAnswerIDAlreadyExist(userAnswerEntity.getExamLogsId(), userAnswerEntity.getQuestionId());
            if (userAnswerID != -1)
            {
                userAnswerEntity.setUserAnswerId(userAnswerID);
                userAnswerDAO.updateUserAnswer(userAnswerEntity);
            }
            else
            {
                userAnswerDAO.addUserAnswer(userAnswerEntity);
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据考试实体获取考试问题
     *
     * @param examEntity 考试实体
     * @return List<QuestionEntity>
     */
    public List<QuestionEntity> getExamQuestionsByExam(ExamEntity examEntity)
    {
        int questionNum = examEntity.getQuestionNum();
        List<QuestionEntity> questions = questionDAO.queryQuestionsByCourseID(examEntity.getCourseId());
        int[] questionIndexes = Algorithm.buildRandomSequence(questions.size());
        List<QuestionEntity> list = new ArrayList<>();
        for (int i = 0; i < questionNum; i++)
        {
            list.add(questions.get(questionIndexes[i]));
        }
        return list;
    }

    /**
     * 获取考试分数
     *
     * @param examLogEntity 考试记录实体
     * @return double 考试分数
     */
    public double getScore(ExamLogEntity examLogEntity)
    {
        return examLogDAO.getExamLogEntityByID(examLogEntity).getScore().doubleValue();
    }

    /**
     * 获取考试报告
     *
     * @param examLogEntity 考试记录实体
     * @return List<ReportEntity> 考试报告列表
     */
    public List<ReportEntity> getReport(ExamLogEntity examLogEntity)
    {
        return reportDAO.getReportByExamLog(examLogEntity);
    }

    /**
     * 提交考试并进行自动批阅
     *
     * @param examLogEntity 考试记录实体
     * @return boolean
     */
    public boolean submitExam(ExamLogEntity examLogEntity)
    {
        examLogDAO.finishExam(examLogEntity);
        return examLogDAO.submitExam(examLogEntity);
    }

    public static void main(String[] args)
    {
        HibernateUtil.getSessionFactory();
        ExamLogEntity examLogEntity = new ExamLogEntity();
        examLogEntity.setExamLogsId(2);
        System.out.println(new ReportDAO().getReportByExamLog(examLogEntity));
    }
}

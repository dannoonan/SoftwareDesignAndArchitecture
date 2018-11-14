package ie.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ie.demo.domain.Reports;

@Mapper
public interface ReportMapper {
	int reportBike(int bikeId, int userId, String reportText);
	List<Reports> getReports();
}

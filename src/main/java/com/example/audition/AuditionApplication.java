package com.example.audition;

import com.example.audition.domain.artist.Artist;
import com.example.audition.domain.artist.repository.ArtistRepository;
import com.example.audition.domain.mento.Mento;
import com.example.audition.domain.mento.repository.MentoRepository;
import com.example.audition.domain.point.Point;
import com.example.audition.domain.point.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AuditionApplication implements CommandLineRunner {

	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private MentoRepository mentoRepository;
	@Autowired
	private PointRepository pointRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuditionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Artist artist1 = new Artist("A001","김스타","F","19970101","1","A엔터테인먼트");
		Artist artist2 = new Artist("A002","조스타","M","19980201","2","B엔터테인먼트");
		Artist artist3 = new Artist("A003","왕스타","M","19990301","3","C엔터테인먼트");
		Artist artist4 = new Artist("A004","정스타","M","20000401","1","D엔터테인먼트");
		Artist artist5 = new Artist("A005","홍스타","F","20001501","2","E엔터테인먼트");

		List<Artist> list1 = Arrays.asList(artist1, artist2, artist3, artist4, artist5);
		artistRepository.saveAll(list1);

		Mento mento1 = new Mento("J001","함멘토");
		Mento mento2 = new Mento("J002","박멘토");
		Mento mento3 = new Mento("J003","장멘토");

		List<Mento> list2 = Arrays.asList(mento1, mento2, mento3);
		mentoRepository.saveAll(list2);

		Point point1 = new Point(2019001L,artist1,mento1,78);
		Point point2 = new Point(2019002L,artist1,mento2,76);
		Point point3 = new Point(2019003L,artist1,mento3,70);
		Point point4 = new Point(2019004L,artist2,mento1,88);
		Point point5 = new Point(2019005L,artist2,mento2,72);
		Point point6 = new Point(2019006L,artist2,mento3,78);
		Point point7 = new Point(2019007L,artist3,mento1,90);
		Point point8 = new Point(2019008L,artist3,mento2,92);
		Point point9 = new Point(2019009L,artist3,mento3,88);
		Point point10 = new Point(2019010L,artist4,mento1,96);
		Point point11 = new Point(2019011L,artist4,mento2,90);
		Point point12 = new Point(2019012L,artist4,mento3,98);
		Point point13 = new Point(2019013L,artist5,mento1,88);
		Point point14 = new Point(2019014L,artist5,mento2,86);
		Point point15 = new Point(2019015L,artist5,mento3,86);

		List<Point> pointList = Arrays.asList(point1, point2, point3, point4, point5, point6
				, point7, point8, point9, point10, point11, point12, point13, point14, point15);
		pointRepository.saveAll(pointList);
	}
}

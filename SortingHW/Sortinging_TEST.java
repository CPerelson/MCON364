package sortingHW;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Sortinging_TEST {

	@Test
	public void testSortEmptyList() {
		List<Student> students = new ArrayList<>();
		List<Student> sortedStudents = MergeSort.sort(students);

		Assert.assertEquals(0, sortedStudents.size());
	}

	@Test
	public void testSortListSizeOne() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("A", 100));

		List<Student> sortedStudents = MergeSort.sort(students);

		Assert.assertEquals(1, sortedStudents.size());
		Assert.assertEquals("A", sortedStudents.get(0).getName());
	}

	@Test
	public void testSortMultipleStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("C", 70));
		students.add(new Student("B", 90));
		students.add(new Student("A", 80));

		List<Student> sortedStudents = MergeSort.sort(students);

		Assert.assertEquals(3, sortedStudents.size());
		Assert.assertEquals("C", sortedStudents.get(0).getName());
		Assert.assertEquals("A", sortedStudents.get(1).getName());
		Assert.assertEquals("B", sortedStudents.get(2).getName());
	}

	@Test
	public void testSortLargeList() {
		List<Student> students = generateStudents(1000);

		long start = System.currentTimeMillis();
		List<Student> sortedStudents = MergeSort.sort(students);
		long end = System.currentTimeMillis();

		Assert.assertEquals(1000, sortedStudents.size());
		Assert.assertTrue(end - start < 1000); // check performance
	}
}

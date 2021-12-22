package lab.square.similaritymeasure.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;

import lab.square.similaritymeasure.core.ISimilarityMeasurer;
import lab.square.similaritymeasure.core.IVector;
import lab.square.similaritymeasure.core.Jaccard;

public class TestSimilarityMeasure {
	@Test
	public void testMeasure() throws Exception {

		IVector v1 = new Vector(new boolean[] { true, true, true, false, false });
		IVector v2 = new Vector(new boolean[] { true, true, false, false, false });
		IVector v3 = new Vector(new boolean[] { true, false, false, false, false });
		
		List<IVector> list = List.of(v1, v2, v3);  // Java 9 �̻�
		
		ISimilarityMeasurer measurer;
		measurer = new Jaccard();	
		
		System.out.println(measurer.compare(v1, v2));
		System.out.println(measurer.compare(v1, v3));
		
		assertTrue(measurer.compare(v1, v2) > measurer.compare(v1, v3));
		assertTrue(measurer.compare(v1, v2) == measurer.compare(v2, v1));
		

		System.out.println(list.size());
		for(int i=0; i<list.size(); i++) {
			IVector target = list.get(i);
			double[] result = measurer.calculateMostSimilar(target, list, i);  // list�� i��° ��Ҹ� Ÿ������, �׿� ���� ����� IVector�� ã�´�. ��, i ����																		// ��, �ڱ� �ڽ��� list���� ������ ä��.
			IVector resultVector = list.get((int)result[1]);
			System.out.print("list�� "+i+"��° ��� ");   target.printAll();   System.out.print(" �� \n");
			System.out.print("list�� "+(int)result[1]+"��° ��� ");   resultVector.printAll();   
			System.out.print(" �� ���� �����ϴ�. (���絵 " + result[0] +")\n\n");
		}
		
		
		
//		int resultIndex = measurer.calculateMostSimilar(v1, list, 0);  // �ϴ� �Ѱ��� test �غ����� ���� ��Ȳ
//		IVector resultVector = list.get(resultIndex);
//		for(int i=0; i<resultVector.getDimension(); i++) {
//			System.out.print(resultVector.getValue(i) + " ");
//		} System.out.println();
		
		
	}

	private class Vector implements IVector {
		private boolean[] vector;

		public Vector(boolean[] vector) {
			this.vector = vector;
		}

		@Override
		public int getDimension() {
			return vector.length;
		}

		@Override
		public boolean getValue(int dimension) {
			return vector[dimension];
		}

		@Override
		public void printAll() {
			for(boolean v : vector) {
				System.out.print(v + " ");
			}
		}
	}
}

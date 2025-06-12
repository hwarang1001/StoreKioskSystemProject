package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.PaymentVO;

public class PaymentManager {
	// ������� ���
	public void list() throws Exception {
		PaymentDAO pd = new PaymentDAO();
		System.out.println("���� ��ü ����Ʈ");
		ArrayList<PaymentVO> paymentList = pd.selectAll();
		if (paymentList.size() <= 0) {
			System.out.println("��������Ʈ�� �����Ͱ� �����ϴ�.");
			return;
		} else if (paymentList == null) {
			System.out.println("��������Ʈ�� ������ �߻��߽��ϴ�.");
			return;
		}
		for (PaymentVO data : paymentList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}
}

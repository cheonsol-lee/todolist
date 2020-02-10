# Android + Kotlin 실습: DataBinding + LiveData + ViewModel + RecylcerView 연계한 Todo List 앱 만들기

	§ 요구 사항
		□ 전체 구성
			® Activity 한 개 
			® Fragment 2개 (Todo List 용 1개; 수정/삭제용 1개)
			® XML layout 4개 (메인 layout 1개; Todo List 용 1개; 수정/삭제 layout 1개; list item layout 1개)
		
		□ Todo List Item
			® CheckBox + TextView 로 구성
			® DataBinding 통해서 CheckBox 의 선택 여부가 양방향으로 Binding이 되도록 만들 것(양방향 데이터결합)
			® 각 Item은 RecyclerView.Adapter 와 RecyclerView.ViewHolder를 통하여 그려지도록 구성
			
		□ 여러 아이템 삭제 기능
			® 별도의 버튼을 누르면 Todo List에서 선택된
			 (CheckBox가 눌려진) 여러 item을 삭제할 수 있도록 할 것.
			
		□ Todo list item 추가 기능
		
		□ Todo list item 수정/삭제 기능
			® Item을 클릭했을 시 별도의 Fragment 를 실행하여 내용을 수정 및 삭제할 수 있게 할 것.
        		® 수정/삭제 Fragment 에서 뒤로 가기 시 원래 List가 나오도록 할 것.(Navigation)

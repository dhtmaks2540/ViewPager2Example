package kr.co.lee.viewpager2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager2 = findViewById(R.id.view_pager2)
        tabLayout = findViewById(R.id.tab_layout)

        // Adapter 생성 후 ViewPager2에 adapter 연결
        val viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter = viewPager2Adapter
        
        // TabLayoutMediator : TabLayout과 ViewPager2를 연결시키는 중재자 역할
        // Tab의 선택이나 ViewPager2가 드래그되는 것을 동기화시킵니다.
        // 생성자의 매개변수는 TabLayout과 ViewPager2 그리고 TabConfigurationStrategy입니다.
        // TabConfigurationStrategy는 Tab의 텍스트와 스티일을 설정
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            // 탭의 제목을 설정하는 구문
            tab.text = "Fragment ${position + 1}"
        }.attach() // attach를 사용하여 TabLayout과 ViewPager를 연결
    }

    // ViewPager2에 Fragment를 연결하기 위해서 FragmentStateAdapter를 상속받는 클래스 정의
    // 생성자로 FragmentActivity 전달
    class ViewPager2Adapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
        // Fragment의 개수
        override fun getItemCount(): Int = 3

        // ViewPager2를 통해 연결할 Fragment들을 제공하는 메서드
        override fun createFragment(position: Int): Fragment {
            // position에 따라 해당하는 Fragment return
            return when(position) {
                0 -> OneFragment()
                1 -> TwoFragment()
                else -> ThreeFragment()
            }
        }
    }
}